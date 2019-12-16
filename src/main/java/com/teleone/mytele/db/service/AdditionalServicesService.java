package com.teleone.mytele.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdditionalServicesService {

    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    public AdditionalService find(String name) {
        return additionalServicesRepository.findByName(name);
    }

    public Optional<AdditionalService> find(Long id) {
        return additionalServicesRepository.findById(id);
    }

    public boolean exists(String name) {
        return additionalServicesRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return additionalServicesRepository.existsById(id);
    }

    public boolean remove(Long id) {
        additionalServicesRepository.deleteById(id);
        return true;
    }

    public static boolean create(AdditionalService additionalServices) {
        if (this.additionalServicesRepository.existsByName(additionalServices.getName())) {
            return false;
        }

        this.additionalServicesRepository.save(additionalServices);
        return true;
    }

    public Set<AdditionalService> getAdditionalServices() {
        HashSet<AdditionalService> set = new HashSet<>();
        additionalServicesRepository.findAll().forEach(set::add);
        return set;
    }

    public Long getAdditionalServicesCount() {
        return additionalServicesRepository.count();
    }


}
