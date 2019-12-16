package com.teleone.mytele.db.service;

import com.teleone.mytele.db.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdditionalServicesService {

    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    public AdditionalServices find(String name) {
        return additionalServicesRepository.findByName(name);
    }

    public Optional<AdditionalServices> find(Long id) {
        return additionalServicesRepository.findById(id);
    }

    public boolean exists(String name) {
        return additionalServicesRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return additionalServicesRepository.existsById(id);
    }

    public boolean remove(Long id, User user) {
        if (user.isEmployee()) {
            additionalServicesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean create(AdditionalServices additionalServices, User user) {
        if (user.isEmployee()) {
            if (!this.additionalServicesRepository.existsByName(additionalServices.getName())) {
                this.additionalServicesRepository.save(additionalServices);
            }
        }
        return false;
    }

    public Set<AdditionalServices> getTickets() {
        HashSet<AdditionalServices> set = new HashSet<>();
        additionalServicesRepository.findAll().forEach(set::add);
        return set;
    }

    public Long getAdditionalServicesCount() {
        return additionalServicesRepository.count();
    }


}
