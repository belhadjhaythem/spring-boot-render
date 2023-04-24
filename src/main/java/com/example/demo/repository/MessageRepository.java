package com.example.demo.repository;

import com.example.demo.model.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {
    @Query(value = "SELECT * FROM message u where u.recipient = :id", nativeQuery = true)
    List<Message> findReceivedMessages(@Param("id") int id);

    @Query(value = "SELECT * FROM message u where u.sender = :id", nativeQuery = true)
    List<Message> findSentMessages(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "delete from message where recipient = :id ", nativeQuery = true)
    void deleteMessagesForArchitect(@Param("id") int id);
}
