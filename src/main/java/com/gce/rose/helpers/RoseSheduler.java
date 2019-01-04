package com.gce.rose.helpers;

import com.gce.rose.model.Item;
import com.gce.rose.repository.ItemRepository;
import com.gce.rose.service.GildedRose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RoseSheduler {

    private GildedRose rose;
    @Autowired
    private ItemRepository repo;

    //0 * * ? * *
//    @Scheduled(cron = "0 * * ? * *")
    public void ItemsUpdatesOnceADay() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> iterable = repo.findAll();
        iterable.forEach(list::add);

        rose = new GildedRose(list.toArray(new Item[list.size()]));

        System.out.println("Original items from database");
        for (Item m : list) {
            System.out.print(m.name + ", ");
            System.out.print(m.sellIn + ", ");
            System.out.println(m.quality + ", ");
        }


        Item[] i = rose.items;
        System.out.println("Original Items in object");
        for (Item item : i) {
            System.out.print(item.name + ", ");
            System.out.print(item.sellIn + ", ");
            System.out.println(item.quality);
        }
        repo.deleteAll();
        rose.updateQuality();
//        repo.deleteAll();

        repo.saveAll(Arrays.asList(rose.items));

        System.out.println("Updated Items in obejct");
        for (Item item : i) {
            System.out.print(item.name + ", ");
            System.out.print(item.sellIn + ", ");
            System.out.println(item.quality);
        }

        List<Item> list1 = new ArrayList<>();
        Iterable<Item> iterable1 = repo.findAll();
        iterable.forEach(list1::add);

        System.out.println("Updated Items from database");
        for (Item a : list1) {
            System.out.print(a.name + ", ");
            System.out.print(a.sellIn + ", ");
            System.out.println(a.quality + ", ");
        }

    }

    public List<Item> simulateChangesDayByDay() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> iterable = repo.findAll();
        iterable.forEach(list::add);

        rose = new GildedRose(list.toArray(new Item[list.size()]));
        rose.updateQuality();
        System.out.println("Updated Items in obejct");
        for (Item item : rose.items) {
            System.out.print(item.name + ", ");
            System.out.print(item.sellIn + ", ");
            System.out.println(item.quality);
        }
        return Arrays.asList(rose.items);
    }
}
