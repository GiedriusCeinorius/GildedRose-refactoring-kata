package com.gce.rose;

import com.gce.rose.model.Item;
import com.gce.rose.service.AnyItem;
import com.gce.rose.service.GildedRose;
import com.gce.rose.service.SimpleItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest

public class RefactoredRoseApplicationTests {

    private GildedRose app;

    @Test
    public void lowersBothValuesForItem() {

        Item item = givenParamaters("Salad", 50, 50);
        app.updateQuality();
        app.anyItem.updateWholeItem(item);
        testItem("Salad", 49, 49);
    }

    @Test
    public void qualityDegradesTwiceAsFast() {

        Item item = givenParamaters("Salad", 0, 50);
        app.updateQuality();
        app.anyItem.updateWholeItem(item);
        testItem("Salad", -1, 48);
    }

    private Item givenParamaters(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        app = new GildedRose(items);
        return items[0];
    }

    private void testItem(String name, int sellIn, int quality) {
        assertEquals(name, app.items[0].name);
        assertEquals(sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
        System.out.println(app.items[0]);
    }

}
