package com.teleone.mytele.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdditionalServicesService {
    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    public AdditionalServices find(String name) { return additionalServicesRepository.findByName(name); }

    public Optional<AdditionalServices> find(Long id) {
        return additionalServicesRepository.findById(id);
    }

    public boolean exists(String name) {
        return additionalServicesRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return additionalServicesRepository.existsById(id);
    }
}
