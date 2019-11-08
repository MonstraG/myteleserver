package com.teleone.mytele.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdditionalServicesService {
    @Autowired
    private AdditionalServiceRepository serviceRepository;

    public AdditionalService find(String name) { return serviceRepository.findByName(name); }

    public Optional<AdditionalService> find(Long id) {
        return serviceRepository.findById(id);
    }

    public boolean exists(String name) {
        return serviceRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return serviceRepository.existsById(id);
    }
}
