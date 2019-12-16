package com.teleone.mytele.db.tariff;

import com.teleone.mytele.db.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TariffService {

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

    public boolean remove(Long id) {
        if (tariffRepository.existsById(id)) {
            tariffRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean create(Tariff tariff) {
        if (tariffRepository.existsByName(tariff.getName())) {
            return false;
        }

        tariffRepository.save(tariff);
        return true;
    }

    public Long getTariffsCount() {
        return tariffRepository.count();
    }

    public Set<Tariff> getTariffs() {
        HashSet<Tariff> set = new HashSet<>();
        tariffRepository.findAll().forEach(set::add);
        return set;
    }

}
