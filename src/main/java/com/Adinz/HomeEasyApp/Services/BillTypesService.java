package com.Adinz.HomeEasyApp.Services;

import com.Adinz.HomeEasyApp.Model.BillTypes;
import com.Adinz.HomeEasyApp.Repositories.BillTypesRepository;
import com.Adinz.HomeEasyApp.exceptions.ProjectIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillTypesService {
    @Autowired
    BillTypesRepository billTypesRepository;

    public BillTypes addOrUpdateBillType(BillTypes billTypes){
        return billTypesRepository.save(billTypes);
    }

    public List<BillTypes> getListOfBills(){
        return billTypesRepository.findAll();
    }
    public BillTypes getBillType(String Id){
        Long id = Long.parseLong(Id);

        Optional<BillTypes> billTypes = billTypesRepository.findById(id);
        if(!billTypes.isPresent()){
            throw new ProjectIDException("BillTypes ID : "+ Id +" doesn't exist");

        }
        return billTypes.get();

    }

    public void deleteBillType(String Id){
        Long id = Long.parseLong(Id);
        Optional<BillTypes> billTypes = billTypesRepository.findById(id);
        if(!billTypes.isPresent()){
            throw new ProjectIDException("BillTypes ID : "+ Id +" doesn't exist");

        }
        billTypesRepository.delete(billTypes.get());


    }

}
