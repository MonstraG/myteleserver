package com.teleone.mytele;

import com.teleone.mytele.db.service.AdditionalService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import com.teleone.mytele.db.tariff.Tariff;
import com.teleone.mytele.db.tariff.TariffService;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

import static com.teleone.mytele.db.role.Role.*;

@SpringBootApplication
public class MyTeleServerApplication {

    @Autowired
    private AdditionalServicesService additionalServicesService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MyTeleServerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBase() {
        Arrays.asList(
                new User("kirill", "123", USER),
                new User("larisa", "123", MOD),
                new User("boris", "123", MOD),
                new User("anna", "123", USER),
                new User("alex", "123", USER),
                new User("admin", "12345", ADMIN)
        ).forEach(user -> userService.save(user));


        Arrays.asList(
                new Tariff("Мой Онлайн", 250, 50, 450, 50, 15),
                new Tariff("Премиум", 1100, 50, 2000, 500, 40),
                new Tariff("Умный", 350, 0, 600, 600, 15)
        ).forEach(tariff -> tariffService.create(tariff));

        Arrays.asList(
                new AdditionalService("Гудок", 10, "Никаких гудков, только музыка"),
                new AdditionalService("Гороскоп", 5, "Получай первсональный гороскоп каждый день"),
                new AdditionalService("Анекдоты", 3, "Смешные анекдоты каждый день"),
                new AdditionalService("Погода", 3, "Прогноз погоды в смс каждое утро"),
                new AdditionalService("Роаминг", 10, "Отключи роуминг для комфортного отдыха")
        ).forEach(additionalService -> additionalServicesService.create(additionalService));
    }

}
