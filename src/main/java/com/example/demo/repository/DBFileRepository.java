package com.example.demo.repository;

import com.example.demo.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

	@Query(value = "SELECT * FROM files f where f.file_type LIKE :type%", nativeQuery = true)
	public List<DBFile> findFileByType(@Param("type") String type);

	@Query(value = "SELECT * FROM files f where f.partner =:partner", nativeQuery = true)
	public DBFile findFileByPartner(@Param("partner") String partner);
}