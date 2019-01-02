package com.gce.rose;

import com.gce.rose.model.Item;
import com.gce.rose.service.GildedRose;
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
    public void lowersBothValuesForEveryItem() {

        Item item = givenParamaters("PO", 10, 10);
        app.updateWholeItem(item);
        testItem("PO", 9, 9);
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
