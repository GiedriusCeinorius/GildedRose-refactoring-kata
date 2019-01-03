package com.gce.rose.service;

import com.gce.rose.model.Item;

public class AgedBrieManagement extends AbstractItemManagement {

    @Override
    public void updateWholeItem(Item item) {
        updateQualityIncrement(item);
        updateSellInByOneDay(item);

        if (item.sellIn < 0) {
            updateQualityIncrement(item);
        }
    }
}
