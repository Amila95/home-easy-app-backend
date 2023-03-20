package com.Adinz.HomeEasyApp.Controllers;

import com.Adinz.HomeEasyApp.Model.BillTypes;
import com.Adinz.HomeEasyApp.Model.BillValues;
import com.Adinz.HomeEasyApp.Model.Item;
import com.Adinz.HomeEasyApp.Services.BillTypesService;
import com.Adinz.HomeEasyApp.Services.BillValuesServices;
import com.Adinz.HomeEasyApp.Services.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/billTypes")
public class BillTypeController {
    @Autowired
    BillTypesService billTypesService;
    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @Autowired
    BillValuesServices billValuesServices;

    @GetMapping
    public ResponseEntity<?> getBillTypesList(){
        //return billTypesService.getListOfBills();
        return new ResponseEntity (billTypesService.getListOfBills(), HttpStatus.OK);

    }

    @GetMapping("/{billTypeId}")
    public Iterable<BillValues> getBillvaluesFormBillTypes(@PathVariable String billTypeId){
        return billValuesServices.getBillValues(billTypeId);

    }

    @PostMapping
    public ResponseEntity<?> addBillTypes(@Valid @RequestBody BillTypes billTypes, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        BillTypes BillType = billTypesService.addOrUpdateBillType(billTypes);
        return new ResponseEntity<BillTypes>(BillType, HttpStatus.CREATED);

    }

    @DeleteMapping("/{billTypeId}")
    public ResponseEntity<?> deleteBillType(@PathVariable String billTypeId){
        billTypesService.deleteBillType(billTypeId);
        return new ResponseEntity<String>("BillType id "+billTypeId+ "was deleted", HttpStatus.OK );


    }




    }
