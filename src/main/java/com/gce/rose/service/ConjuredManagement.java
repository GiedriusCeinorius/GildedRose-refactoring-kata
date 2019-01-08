package com.gce.rose.service;

import com.gce.rose.model.Item;

public class ConjuredManagement extends AbstractItemManagement {

    private int timesMoreThanSimpleItem = 2;

    @Override
    public void updateWholeItem(Item item) {
        updateQualityDecrementTwiceAsFast(item, timesMoreThanSimpleItem);
        updateSellInByOneDay(item);

        if (item.sellIn < 0) {
            updateQualityDecrementTwiceAsFast(item, timesMoreThanSimpleItem);
        }
    }
}
