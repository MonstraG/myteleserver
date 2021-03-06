package com.teleone.mytele.db.tariff;

import org.springframework.data.repository.CrudRepository;

public interface TariffRepository extends CrudRepository<Tariff, Long> {
    Tariff findById(long id);

    Tariff findByName(String name);

    boolean existsById(long id);

    boolean existsByName(String string);

    boolean existsByIdOrName(long id, String name);

}
