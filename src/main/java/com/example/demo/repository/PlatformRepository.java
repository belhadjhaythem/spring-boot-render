package com.example.demo.repository;

import com.example.demo.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlatformRepository extends JpaRepository<Platform, String> {

    @Query(value = "SELECT * FROM platform u where u.client_code = :clientCode", nativeQuery = true)
    Platform getPlatformByCode(@Param("clientCode") String clientCode);
}
