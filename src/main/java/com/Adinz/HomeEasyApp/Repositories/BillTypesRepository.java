package com.Adinz.HomeEasyApp.Repositories;

import com.Adinz.HomeEasyApp.Model.BillTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTypesRepository extends JpaRepository<BillTypes, Long> {
}
