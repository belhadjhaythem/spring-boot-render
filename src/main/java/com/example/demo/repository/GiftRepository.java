package com.example.demo.repository;

import com.example.demo.model.Gift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends CrudRepository<Gift, String> {

}
