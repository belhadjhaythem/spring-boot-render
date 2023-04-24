package com.example.demo.repository;

import com.example.demo.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepository extends JpaRepository<Config, String> {

	@Query(value = "SELECT * FROM config f where f.type = :type", nativeQuery = true) 
	public List<Config> findConfigByType(@Param("type") String type);
}
