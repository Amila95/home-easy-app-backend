package com.Adinz.HomeEasyApp.Controllers;

import com.Adinz.HomeEasyApp.Model.Item;
import com.Adinz.HomeEasyApp.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/restore")
@CrossOrigin
public class RestoreController {
    @Autowired
    ItemService itemService;

    @GetMapping
    public Iterable<Item> getListItems(){
        return itemService.findAllItem(true);
    }

    @GetMapping("restoreDateBitween")
    public List<Item> getRanges(@RequestParam("start") String start, @RequestParam("end") String end) throws ParseException {
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM, yyyy");
//        LocalDate startDate = LocalDate.parse(start, format);
//        LocalDate endDate = LocalDate.parse(end, format);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(start);
        Date endDate = dateFormat.parse(end);



        return itemService.findByDateBetween(startDate, endDate);
    }
}
