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

    private List<Item> simulatedList = new ArrayList<>();
    // Every minute
    // 0 * * ? * *
    // Every day at midnight
    // 0 0 0 * * ?
    @Scheduled(cron = "0 0 0 * * ?")
    public void ItemsUpdatesOnceADay() {

        List<Item> list = new ArrayList<>();
        Iterable<Item> iterable = repo.findAll();
        iterable.forEach(list::add);

        rose = new GildedRose(list.toArray(new Item[list.size()]));

        repo.deleteAll();
        rose.updateQuality();
        repo.saveAll(Arrays.asList(rose.items));

    }

    public List<Item> simulateChangesDayByDay() {

        if (simulatedList.size() == 0) {
            Iterable<Item> iterable = repo.findAll();
            iterable.forEach(simulatedList::add);
        }

        rose = new GildedRose(simulatedList.toArray(new Item[simulatedList.size()]));
        rose.updateQuality();
        simulatedList = Arrays.asList(rose.items);

        return simulatedList;
    }
}
