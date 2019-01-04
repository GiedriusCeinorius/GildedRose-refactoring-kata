package com.gce.rose.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Item implements Serializable {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}


