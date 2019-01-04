package com.gce.rose.service;

import com.gce.rose.model.Item;
import com.gce.rose.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class GildedRose {

    public Item[] items;
    private AbstractItemManagement specificItem;
    private ExecutorService executor = null;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        processItemsConcurrently();
    }

    private void processItemsConcurrently() {
        Map<Item, AbstractItemManagement> map = autoSelectItem();
        try {
            executor = Executors.newFixedThreadPool(4);

            for (Map.Entry<Item, AbstractItemManagement> entry : map.entrySet()) {
                executor.execute(() -> entry.getValue().updateWholeItem(entry.getKey()));
            }
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
        if (executor != null) {
            try {
                executor.awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage() + " threads did not make it in 'processItemsConcurrently' method ");
                e.printStackTrace();
            }
        }
    }

    private Map<Item, AbstractItemManagement> autoSelectItem() {
        Map<Item, AbstractItemManagement> map = new LinkedHashMap<>();
        for (Item item : items) {
            String name = item.name;
            switch (name) {
                case "Aged Brie":
                    specificItem = new AgedBrieManagement();
                    map.put(item, specificItem);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    specificItem = new BackstagePassesManagement();
                    map.put(item, specificItem);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    specificItem = new SulfurasManagement();
                    map.put(item, specificItem);
                    break;
                case "Conjured":
                    specificItem = new ConjuredManagement();
                    map.put(item, specificItem);
                    break;
                default:
                    specificItem = new SimpleItemManagement();
                    map.put(item, specificItem);
                    break;
            }
        }
        return map;
    }
}

//    public void updateQuality() {
//        for (int i = 0; i < items.length; i++) {
//            if (!items[i].name.equals("Aged Brie") && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                if (items[i].quality > 0) {
//                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                        items[i].quality = items[i].quality - 1;
//                    }
//                }
//            } else {
//                if (items[i].quality < 50) {
//                    items[i].quality = items[i].quality + 1;
//
//                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (items[i].sellIn < 11) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1;
//                            }
//                        }
//
//                        if (items[i].sellIn < 6) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1;
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                items[i].sellIn = items[i].sellIn - 1;
//            }
//
//            if (items[i].sellIn < 0) {
//                if (!items[i].name.equals("Aged Brie")) {
//                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (items[i].quality > 0) {
//                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                                items[i].quality = items[i].quality - 1;
//                            }
//                        }
//                    } else {
//                        items[i].quality = items[i].quality - items[i].quality;
//                    }
//                } else {
//                    if (items[i].quality < 50) {
//                        items[i].quality = items[i].quality + 1;
//                    }
//                }
//            }
//        }
//    }


// Paprasta preke - mazejant dienoms (sellIn) mazeja ir quality po 1, sellIn pasiekus 0 quality mazeja dvigubai greiciau t.y po 2.
// Quality neina i minusa.
// Aged Brie - dienoms mazejant quality dideja po 1, pasiekus 0 dideja dvigubai greiciau t.y po 2.
// Quality negali virsyti 50.
// Sulfuras - negali buti parduotas, t.y sellIn ir quality stovi vietoje visada. Jo quality 80.
// Backstage passes - mazejant dienoms (sellIn) quality dideja  po 1, dienoms nukritus iki 10, quality pradeda dideti po 2,
// o nukritus iki 5, dideja po 3. Kai sellIn pasiekia 0, quality krenta iskart iki 0.

// Conjured - quality mazeja dvigubai greiciau nei paprastu prekiu (tai po 2 turbut)
// Negalima liesti Item klases