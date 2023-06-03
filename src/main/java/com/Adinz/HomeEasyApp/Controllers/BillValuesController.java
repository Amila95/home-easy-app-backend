package com.Adinz.HomeEasyApp.Controllers;

import com.Adinz.HomeEasyApp.Model.BillTypes;
import com.Adinz.HomeEasyApp.Model.BillValues;
import com.Adinz.HomeEasyApp.PalyLoad.BillValueRequest;
import com.Adinz.HomeEasyApp.Services.BillValuesServices;
import com.Adinz.HomeEasyApp.Services.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/billValues")
public class BillValuesController {
    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @Autowired
    BillValuesServices billValuesServices;

    @PostMapping
    public ResponseEntity<?> addBillValue(@Valid @RequestBody BillValueRequest billValues, BindingResult result ){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        BillValues billValue = billValuesServices.addBillValues(billValues);
        return new ResponseEntity<BillValues>(billValue,HttpStatus.CREATED);
    }

    @GetMapping("/{billType_id}")
    public ResponseEntity<?> GetBillValues(@PathVariable String billValue_id){
        return new ResponseEntity(billValuesServices.getBillValues(billValue_id), HttpStatus.OK);
    }

    @PatchMapping("/{billValue_id}")
    public ResponseEntity<?> updateBillValue(@Valid @RequestBody BillValues billValues, BindingResult result, @PathVariable String billValue_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        BillValues billValue = billValuesServices.updateBillValues(billValues,billValue_id);
        return new ResponseEntity<BillValues>(billValue,HttpStatus.CREATED);

    }


}
