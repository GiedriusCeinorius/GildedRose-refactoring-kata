package com.gce.rose.service;

import com.gce.rose.model.Item;

public class SimpleItemManagement extends AbstractItemManagement {

    public void updateWholeItem(Item item) {
        updateQualityDecrement(item);
        updateSellInByOneDay(item);

        if (item.sellIn < 0) {
            updateQualityDecrement(item);
        }
    }
}
