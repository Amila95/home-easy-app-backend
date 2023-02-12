package com.Adinz.HomeEasyApp.Repositories;

import com.Adinz.HomeEasyApp.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
