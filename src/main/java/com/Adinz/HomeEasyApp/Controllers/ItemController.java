package com.Adinz.HomeEasyApp.Controllers;

import com.Adinz.HomeEasyApp.Model.Item;
import com.Adinz.HomeEasyApp.PalyLoad.ItemRequest;
import com.Adinz.HomeEasyApp.Repositories.ItemRepository;
import com.Adinz.HomeEasyApp.Services.ItemService;
import com.Adinz.HomeEasyApp.Services.MapValidationErrorService;
import com.Adinz.HomeEasyApp.exceptions.ProjectIDException;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping
    public Iterable<Item> getListItems(){
            return itemService.findAllItem(false);
    }

//    @GetMapping
//    public Iterable<Item> getListItem(@RequestParam String reOder){
//        if(reOder != null && reOder.equalsIgnoreCase("true")){
//            return itemService.findAllItem(true);
//        }else {
//            return itemService.findAllItem(false);
//        }
//    }


    @PostMapping
    public ResponseEntity<?> addItem(@Valid @RequestBody ItemRequest request, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Item item = itemService.saveOrUpdateItem(request);
        return new ResponseEntity<Item>(item, HttpStatus.CREATED);

    }

    @GetMapping("/{ItemId}")
    public ResponseEntity<?> getItemID(@PathVariable String ItemId){
        Item item = itemService.getItemById(ItemId);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @DeleteMapping("/{ItemId}")
    public ResponseEntity<?> deleteItemID(@PathVariable String ItemId){
        itemService.deleteItem(ItemId);
        return new ResponseEntity<String>("Item with ID "+ItemId+ "was deleted", HttpStatus.OK );
    }

    @DeleteMapping
    public ResponseEntity<?> deleteItems(@RequestBody List<Item> items){
        System.out.println("deleteItems");
        itemService.deleteItems(items);
        return new ResponseEntity<String>("Items deleted successfully", HttpStatus.OK );
    }

    @PutMapping("/{ItemId}")
    public ResponseEntity<?> updateItemID(@Valid @RequestBody ItemRequest request, @PathVariable String ItemId,BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        Item item = itemService.updateItem(ItemId,request);
        return new ResponseEntity<Item>(item, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateItems(@RequestBody List<Item> items,BindingResult result) {
        itemService.updateItems(items);
        return new ResponseEntity<String>("Items update successfully", HttpStatus.CREATED);
    }




}
