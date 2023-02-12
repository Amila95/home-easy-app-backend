package com.Adinz.HomeEasyApp.Repositories;

import com.Adinz.HomeEasyApp.Model.BillValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillValuesRepository extends JpaRepository<BillValues,Long> {
}
