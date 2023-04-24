package com.example.demo.repository;

import com.example.demo.model.Architect;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "SELECT * FROM user u where u.username = :input or u.email = :input", nativeQuery = true)
	User findUserByEmailOrUsername(@Param("input") String input);
	
	@Query(value = "SELECT * FROM architect a inner join user u on u.id=a.id where u.username = :input or u.email = :input LIMIT 1", nativeQuery = true)
	Architect findArchitectByEmailOrUsername(@Param("input") String input);


	@Transactional
	@Modifying
	@Query(value = "UPDATE user set password=md5(:password) where id = 1", nativeQuery = true)
	void updatePassword(@Param("password") String password);
}