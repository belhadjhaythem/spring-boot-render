package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

	@Query(value = "SELECT architect_id FROM client u where u.id = :id", nativeQuery = true)
	Integer findArchitectByClient(@Param("id") int id);

	@Query(value = "SELECT * FROM client f where f.architect_id = :id", nativeQuery = true)
	List<Client> findClientsForArchitect(@Param("id") int id);
}
