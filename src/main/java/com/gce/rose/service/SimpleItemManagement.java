package com.gce.rose.service;

import com.gce.rose.model.Item;

public class SimpleItemManagement extends AbstractItemManagement {

    @Override
    public void updateWholeItem(Item item) {
        updateQualityDecrement(item);
        updateSellInByOneDay(item);

        if (item.sellIn < 0) {
            updateQualityDecrement(item);
        }
    }
}
