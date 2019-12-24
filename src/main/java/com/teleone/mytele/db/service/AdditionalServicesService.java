package com.teleone.mytele.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean create(AdditionalService additionalServices) {
        if (this.additionalServicesRepository.existsByName(additionalServices.getName())) {
            return false;
        }

        this.additionalServicesRepository.save(additionalServices);
        return true;
    }

    public List<AdditionalService> getAdditionalServices() {
        ArrayList<AdditionalService> set = new ArrayList<>();
        additionalServicesRepository.findAll().forEach(set::add);
        return set;
    }

    public Long getAdditionalServicesCount() {
        return additionalServicesRepository.count();
    }


}
