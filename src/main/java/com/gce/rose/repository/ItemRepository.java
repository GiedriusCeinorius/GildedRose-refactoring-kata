package com.gce.rose.repository;

import com.gce.rose.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ElasticsearchCrudRepository<Item, String> {
}
