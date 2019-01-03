package com.gce.rose;

import com.gce.rose.model.Item;
import com.gce.rose.service.GildedRose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class RoseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoseApplication.class, args);

        Item[] items = new Item[]{
                new Item("Salad", 50, 50),
                new Item("Bread", 0, 50),
                new Item("Butter", 1, 0),
                new Item("Aged Brie", 50, 1),
                new Item("Aged Brie", 50, 50),
                new Item("Aged Brie", 0, 1),
                new Item("Sulfuras, Hand of Ragnaros", 50, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 11, 15),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 16),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 22),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 22),
                new Item("Conjured", 0, 22)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        for (Item item : items) {
            System.out.print(item.name + ", ");
            System.out.print(item.sellIn + ", ");
            System.out.println(item.quality);
        }
    }

}

