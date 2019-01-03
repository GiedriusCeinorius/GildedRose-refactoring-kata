package com.gce.rose.service;

import com.gce.rose.model.Item;

public class BackstagePassesManagement extends AbstractItemManagement {

    @Override
    public void updateWholeItem(Item item) {

        updateQualityIncrement(item);

        if (item.sellIn < 11) {
            updateQualityIncrement(item);
        }
        if (item.sellIn < 6) {
            updateQualityIncrement(item);
        }

        updateSellInByOneDay(item);

        if (item.sellIn < 0) {
            item.quality -= item.quality;
        }

    }
}

