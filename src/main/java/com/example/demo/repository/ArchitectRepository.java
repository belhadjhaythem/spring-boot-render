package com.example.demo.repository;

import com.example.demo.model.Architect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectRepository extends CrudRepository<Architect, Integer> {

	
}