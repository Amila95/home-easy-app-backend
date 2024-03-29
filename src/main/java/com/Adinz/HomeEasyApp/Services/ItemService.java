package com.Adinz.HomeEasyApp.Services;

import com.Adinz.HomeEasyApp.Model.Item;
import com.Adinz.HomeEasyApp.PalyLoad.ItemRequest;
import com.Adinz.HomeEasyApp.Repositories.ItemRepository;
import com.Adinz.HomeEasyApp.exceptions.ProjectIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item saveOrUpdateItem(ItemRequest request){
        Optional<Item> item1 = itemRepository.findByNameAndPickupFalse(request.getName());
        if(!item1.isPresent()) {
            Item item = new Item();
            item.setName(request.getName());
            item.setQuantity(request.getQuantity());
            item.setStatus(request.getStatus());
            item.setShop(request.getShop());

            return itemRepository.save(item);
        }else{
            item1.get().setQuantity(request.getQuantity());
            item1.get().setStatus(request.getStatus());
            item1.get().setShop(request.getShop());
            return itemRepository.save(item1.get());
        }
    }

    public Item getItemById(String Id){
        Long id = Long.parseLong(Id);
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ProjectIDException("Project ID "+ Id +" doesn't exist");
        }
        return item.get();


    }

    public Iterable<Item> findAllItem(boolean b){
        return itemRepository.findByPickup(b);
    }

    public void deleteItem(String Id){
        Long id = Long.parseLong(Id);
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ProjectIDException("cannot find the item" + id+ ".");
        }
        itemRepository.delete(item.get());


    }

    public Item updateItem(String Id, ItemRequest request){
        Long id = Long.parseLong(Id);
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ProjectIDException("cannot find the item " + id+ ".");
        }

        Item item1 = item.get();
        item1.setName(request.getName());
        item1.setQuantity(request.getQuantity());
        item1.setStatus(request.getStatus());
        item1.setShop(request.getShop());
        if(request.isPickup()){
            item1.setPickup(true);
            item1.setPickupDate(new Date());
        }
        return itemRepository.save(item1);


    }

    public void deleteItems(List<Item> items) {
        for(Item item: items){
            itemRepository.delete(item);
        }
    }

    public void updateItems(List<Item> items) {
        for(Item item: items){
            item.setPickup(true);
            item.setPickupDate(new Date());
            itemRepository.save(item);
        }

    }

    public List<Item> findByDateBetween(Date start, Date end) {
        //itemRepository.findByPickUp_At()
        return itemRepository.findByPickupDateBetween(start,end);
    }
}
