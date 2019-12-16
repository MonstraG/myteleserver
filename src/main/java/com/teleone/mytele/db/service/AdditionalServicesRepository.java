package com.teleone.mytele.db.service;

import org.springframework.data.repository.CrudRepository;

public interface AdditionalServicesRepository extends CrudRepository<AdditionalService, Long> {
    AdditionalService findById(long id);

    AdditionalService findByName(String name);

    boolean existsById(long id);

    boolean existsByName(String string);

    boolean existsByIdOrName(long id, String name);
}
