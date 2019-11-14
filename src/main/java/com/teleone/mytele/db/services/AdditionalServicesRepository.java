package com.teleone.mytele.db.services;

import org.springframework.data.repository.CrudRepository;

public interface AdditionalServicesRepository extends CrudRepository<AdditionalServices, Long> {
    AdditionalServices findById(long id);

    AdditionalServices findByName(String name);

    boolean existsById(long id);

    boolean existsByName(String string);

    boolean existsByIdOrName(long id, String name);
}
