package com.gce.rose;

import com.gce.rose.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.gce.rose.service.GildedRose;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoseApplicationTests {

    private GildedRose app;

    //    At the end of each day our system lowers both values for every item
    @Test
    public void lowersBothValuesForItem() {
        givenParamaters("Salad", 50, 50);
        app.updateQuality();
        testItem("Salad", 49, 49);
    }

    //    Once the sell by date has passed, Quality degrades twice as fast
    @Test
    public void qualityDegradesTwiceAsFast() {
        givenParamaters("Salad", 0, 50);
        app.updateQuality();
        testItem("Salad", -1, 48);
    }

    //    The Quality of an item is never negative
    @Test
    public void qualityIsNeverNegative() {
        givenParamaters("Salad", 1, 0);
        app.updateQuality();
        testItem("Salad", 0, 0);
    }

    //        "Aged Brie" actually increases in Quality the older it gets
    @Test
    public void agedBrieQuality() {
        givenParamaters("Aged Brie", 50, 1);
        app.updateQuality();
        testItem("Aged Brie", 49, 2);
    }

    //    The Quality of an item is never more than 50
    @Test
    public void qualityNeverMoreThan50() {
        givenParamaters("Aged Brie", 50, 50);
        app.updateQuality();
        testItem("Aged Brie", 49, 50);
    }

    //    Once the sell by date has passed, Aged Brie Quality increases twice as fast
    @Test
    public void agedBrieQuality0() {
        givenParamaters("Aged Brie", 0, 1);
        app.updateQuality();
        testItem("Aged Brie", -1, 3);
    }

    //    "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    @Test
    public void sulfurasQuality() {
        givenParamaters("Sulfuras, Hand of Ragnaros", 50, 80);
        app.updateQuality();
        testItem("Sulfuras, Hand of Ragnaros", 50, 80);
    }

    //    "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
//    Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less
    @Test
    public void backstagePassesQuality() {

        //From start till 10 increases by 1
        givenParamaters("Backstage passes to a TAFKAL80ETC concert", 11, 15);
        app.updateQuality();
        testItem("Backstage passes to a TAFKAL80ETC concert", 10, 16);

        //From 10 to 5 increases by 2
        givenParamaters("Backstage passes to a TAFKAL80ETC concert", 10, 16);
        app.updateQuality();
        testItem("Backstage passes to a TAFKAL80ETC concert", 9, 18);

        //From 5 to 0 increases by 3
        givenParamaters("Backstage passes to a TAFKAL80ETC concert", 5, 22);
        app.updateQuality();
        testItem("Backstage passes to a TAFKAL80ETC concert", 4, 25);
    }

    //At 0 and below Quality drops to 0
    @Test
    public void backstagePassesQualityAt0() {
        givenParamaters("Backstage passes to a TAFKAL80ETC concert", 0, 22);
        app.updateQuality();
        testItem("Backstage passes to a TAFKAL80ETC concert", -1, 0);
    }

    // "Conjured" items degrade in Quality twice as fast as normal items
    // Comment this method when testing with legacy code
    @Test
    public void conjuredQualityDegrates() {
        givenParamaters("Conjured", 0, 22);
        app.updateQuality();
        testItem("Conjured", -1, 18);
    }


    private void givenParamaters(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        app = new GildedRose(items);
    }

    private void testItem(String name, int sellIn, int quality) {
        assertEquals(name, app.items[0].name);
        assertEquals(sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
        System.out.println(app.items[0]);
    }
}

