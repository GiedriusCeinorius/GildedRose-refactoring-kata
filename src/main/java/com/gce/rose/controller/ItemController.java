package com.gce.rose.controller;

import com.gce.rose.helpers.RoseSheduler;
import com.gce.rose.model.Item;
import com.gce.rose.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository repo;
    @Autowired
    RoseSheduler roseSheduler;

    @RequestMapping("/searchAll")
    public List<Item> getAllItems() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> iterable = repo.findAll();
        iterable.forEach(list::add);
        return list;

    }

    @RequestMapping("/simulateItemChangesDayByDay")
    public List<Item> getAllItemsSimulation() {
        return roseSheduler.simulateChangesDayByDay();

    }

}


