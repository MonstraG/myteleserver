package com.teleone.mytele.db.tariff;

import com.teleone.mytele.db.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TariffsService {
    @Autowired
    private TariffRepository tariffRepository;

    public Tariff find(String name) {
        return tariffRepository.findByName(name);
    }

    public Optional<Tariff> find(Long id) {
        return tariffRepository.findById(id);
    }

    public boolean exists(String name) {
        return tariffRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return tariffRepository.existsById(id);
    }

    public boolean remove(Long id, User user) {
        if (user.getRole().name() == "MOD" || user.getRole().name() == "ADMIN") {
            tariffRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean create(Tariff tariff, User user) {
        if (user.getRole().name() == "MOD" || user.getRole().name() == "ADMIN") {
            if (!tariffRepository.existsByName(tariff.getName())) {
                tariffRepository.save(tariff);
            }
        }
        return false;
    }

    public Long getTariffsCount() {
        return tariffRepository.count();
    }

    public Set<Tariff> getTickets() {
        HashSet<Tariff> set = new HashSet<>();
        tariffRepository.findAll().forEach(set::add);
        return set;
    }

}
