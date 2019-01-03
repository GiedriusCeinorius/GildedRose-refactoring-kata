package com.gce.rose.controller;

import com.gce.rose.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/searchAll")
public class SearchController {

    @Autowired
    private ItemRepository repo;


}


