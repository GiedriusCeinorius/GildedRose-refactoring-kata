package com.gce.rose.service;

import com.gce.rose.model.Item;

public class AnyItem {

    public void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    public void decrementSellInByOneDay(Item item) {
        item.sellIn -= 1;
    }

    public void updateWholeItem(Item item) {
        decrementQuality(item);
        decrementSellInByOneDay(item);

        if (item.sellIn < 0) {
            decrementQuality(item);
        }
    }
}
