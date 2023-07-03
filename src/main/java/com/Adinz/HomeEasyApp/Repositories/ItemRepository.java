package com.Adinz.HomeEasyApp.Repositories;

import com.Adinz.HomeEasyApp.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPickup(boolean pickup);

    Optional<Item> findByName(String name);

    List<Item> findByPickupDateBetween(Date start, Date end);

    Optional<Item> findByNameAndPickupFalse(String name);
}
