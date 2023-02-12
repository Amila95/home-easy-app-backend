package com.Adinz.HomeEasyApp.Repositories;

import com.Adinz.HomeEasyApp.Model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
