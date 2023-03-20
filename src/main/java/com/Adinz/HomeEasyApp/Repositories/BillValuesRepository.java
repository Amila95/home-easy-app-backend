package com.Adinz.HomeEasyApp.Repositories;

import com.Adinz.HomeEasyApp.Model.BillTypes;
import com.Adinz.HomeEasyApp.Model.BillValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillValuesRepository extends JpaRepository<BillValues,Long> {

    List<BillValues> findByBillTypes(BillTypes id);
}
