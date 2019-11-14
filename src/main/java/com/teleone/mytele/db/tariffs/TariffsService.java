package com.teleone.mytele.db.tariffs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TariffsService {
    @Autowired
    private TariffRepository tariffRepository;

    public Tariff find(String name) { return tariffRepository.findByName(name); }

    public Optional<Tariff> find(Long id) {
        return tariffRepository.findById(id);
    }

    public boolean exists(String name) {
        return tariffRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return tariffRepository.existsById(id);
    }

    //todo:
    // add deletion and creation with check that User has role moderator or admin
}
