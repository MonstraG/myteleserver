package com.teleone.mytele.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdditionalServicesService {
    @Autowired
    private AdditionalServicesRepository serviceRepository;

    public AdditionalServices find(String name) { return serviceRepository.findByName(name); }

    public Optional<AdditionalServices> find(Long id) {
        return serviceRepository.findById(id);
    }

    public boolean exists(String name) {
        return serviceRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return serviceRepository.existsById(id);
    }
}
