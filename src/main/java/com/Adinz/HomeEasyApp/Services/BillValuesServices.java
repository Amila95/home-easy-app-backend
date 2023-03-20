package com.Adinz.HomeEasyApp.Services;

import com.Adinz.HomeEasyApp.Model.BillTypes;
import com.Adinz.HomeEasyApp.Model.BillValues;
import com.Adinz.HomeEasyApp.Repositories.BillTypesRepository;
import com.Adinz.HomeEasyApp.Repositories.BillValuesRepository;
import com.Adinz.HomeEasyApp.exceptions.BillTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillValuesServices {
    @Autowired
    BillValuesRepository billValuesRepository;
    @Autowired
    BillTypesRepository billTypesRepository;

    public List<BillValues> addBillValues(){
        return billValuesRepository.findAll();
    }

    public BillValues addBillValues(BillValues billValues, String billTypeId){
        Long id = Long.parseLong(billTypeId);

        Optional<BillTypes> billTypes = billTypesRepository.findById(id);
        if(!billTypes.isPresent()){
            throw new BillTypeNotFoundException("BillType not found");
        }
        billValues.setBillTypes(billTypes.get());
        return billValuesRepository.save(billValues);


        //BillTypes billTypes = billTypesRepository.findById(id).get();


    }

    public Iterable<BillValues> getBillValues(String billTypeId){
        Long id = Long.parseLong(billTypeId);
        Optional<BillTypes> billTypes = billTypesRepository.findById(id);
        if(!billTypes.isPresent()){
            throw new BillTypeNotFoundException("BillType not found");
        }
       // billValues.setBillTypes(billTypes);
       return billValuesRepository.findByBillTypes(billTypes.get());

    }

    public BillValues updateBillValues(BillValues billValues, String billValueId){
        Long id = Long.parseLong(billValueId);
        Optional<BillValues> billValue = billValuesRepository.findById(id);
        if(!billValue.isPresent()){
            throw new BillTypeNotFoundException("BillValue not found");
        }
        System.out.println( "id get from billvalue "+billValue.get().getId());
        System.out.println("id in params "+id);

        if(billValues.getId() != id){
            throw new BillTypeNotFoundException("BillValue not match");
        }
        BillValues currentBillValue = billValue.get();
        currentBillValue.setBillTypes(billValue.get().getBillTypes());
        currentBillValue = billValues;
        return billValuesRepository.save(currentBillValue);

    }

}
