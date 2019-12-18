package com.teleone.mytele;

import com.teleone.mytele.db.service.AdditionalService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import com.teleone.mytele.db.tariff.Tariff;
import com.teleone.mytele.db.tariff.TariffService;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.teleone.mytele.db.role.Role.*;

@Component
public class MockData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private AdditionalServicesService additionalServicesService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private UserService userService;

    private ApplicationArguments appArgs;

    public MockData(ApplicationArguments appArgs) {
        this.appArgs = appArgs;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (!appArgs.getNonOptionArgs().contains("debug")) {
            return;
        }

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
