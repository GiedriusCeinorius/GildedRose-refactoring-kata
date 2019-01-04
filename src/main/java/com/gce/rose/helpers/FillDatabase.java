package com.gce.rose.helpers;

import com.gce.rose.model.Item;
import com.gce.rose.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class FillDatabase {

    @Autowired
    private ItemRepository repo;

    @PostConstruct
    public void Init() {

        List<Item> list = new ArrayList<>();
        list.add(new Item("Salad", 50, 50));
        list.add(new Item("Bread", 0, 50));
        list.add(new Item("Butter", 1, 0));
        list.add(new Item("Aged Brie", 50, 1));
        list.add(new Item("Aged Brie", 50, 50));
        list.add(new Item("Aged Brie", 0, 1));
        list.add(new Item("Sulfuras, Hand of Ragnaros", 50, 80));
        list.add(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 15));
        list.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 16));
        list.add(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 22));
        list.add(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 22));
        list.add(new Item("Conjured", 0, 22));

        repo.saveAll(list);

    }
}
