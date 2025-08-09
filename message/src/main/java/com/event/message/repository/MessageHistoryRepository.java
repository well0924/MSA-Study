package com.event.message.repository;

import com.event.message.entity.MessageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageHistoryRepository extends JpaRepository<MessageHistoryEntity,Long> {

    List<MessageHistoryEntity> findByMemberId(Long memberId);

    List<MessageHistoryEntity> findByUserId(String userId);

    void deleteByMemberId(Long memberId);

}
