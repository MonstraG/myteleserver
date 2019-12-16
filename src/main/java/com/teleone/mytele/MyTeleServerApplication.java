package com.teleone.mytele;

import com.teleone.mytele.db.service.AdditionalService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import com.teleone.mytele.db.tariff.Tariff;
import com.teleone.mytele.db.tariff.TariffService;
import com.teleone.mytele.db.ticket.Ticket;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static com.teleone.mytele.db.role.Role.*;

@SpringBootApplication
public class MyTeleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTeleServerApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBase() {
        User kirill = new User("kirill", "123", USER);
        UserService.save(kirill);
        User alex = new User("alex", "123", USER);
        UserService.save(alex);
        User anna = new User("anna", "123", USER);
        UserService.save(anna);
        User boris = new User("boris", "123", MOD);
        UserService.save(boris);
        User larisa = new User("larisa", "123", MOD);
        UserService.save(larisa);
        User admin = new User("admin", "12345", ADMIN);
        UserService.save(admin);

        Tariff myOnline = new Tariff("myOnline", 250, 50, 450, 50, 15);
        Tariff premium = new Tariff("premium", 1100, 50, 2000, 500, 40);
        Tariff smart = new Tariff("smart", 350, 0, 600, 600, 15);
        TariffService.create(myOnline);
        TariffService.create(premium);
        TariffService.create(smart);

        AdditionalService gudok = new AdditionalService("gudok", 10, "Никаких гудков, только музыка");
        AdditionalService horoscope = new AdditionalService("horoscope", 5, "Получай первсональный гороскоп каждый день");
        AdditionalService joke = new AdditionalService("joke", 3, "Смешные анекдоты каждый день");
        AdditionalService weather = new AdditionalService("weather", 3, "Прогноз погоды в смс каждое утро");
        AdditionalService roamingOff = new AdditionalService("roamingOff", 10, "Отключи роуминг для комфортного отдыха");
        AdditionalServicesService.create(gudok);
        AdditionalServicesService.create(horoscope);
        AdditionalServicesService.create(joke);
        AdditionalServicesService.create(weather);
        AdditionalServicesService.create(roamingOff);

    }


}
