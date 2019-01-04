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
    List<Item> list = null;
//0 * * ? * *

    @Scheduled(cron = "0 0/1 * 1/1 * ? *")
    public void ItemsUpdatesOnceADay() {
        list = new ArrayList<>();
        Iterable<Item> iterable = repo.findAll();
        iterable.forEach(list::add);

        rose = new GildedRose(list.toArray(new Item[list.size()]));

        rose.updateQuality();
        repo.deleteAll();
        repo.saveAll(Arrays.asList(rose.items));
    }
}
