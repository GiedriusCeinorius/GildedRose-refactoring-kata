package com.gce.rose.service;

import com.gce.rose.model.Item;

public class ConjuredManagement extends AbstractItemManagement {
    @Override
    public void updateWholeItem(Item item) {
        updateQualityDecrementTwiceAsFast(item);
        updateSellInByOneDay(item);

        if (item.sellIn < 0) {
            updateQualityDecrementTwiceAsFast(item);
        }
    }
}
