package com.gce.rose.service;

import com.gce.rose.model.Item;

public abstract class AbstractItemManagement {

    public void updateQualityDecrement(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    public void updateQualityIncrement(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    public void updateSellInByOneDay(Item item) {
        item.sellIn -= 1;
    }

    public void updateQualityDecrementTwiceAsFast(Item item, int timesMoreThanSimpleItem) {

        if (timesMoreThanSimpleItem > 0) {
            updateQualityDecrement(item);
            updateQualityDecrementTwiceAsFast(item, timesMoreThanSimpleItem - 1);
        }
    }

    public abstract void updateWholeItem(Item item);

}
