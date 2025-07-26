package com.event.msalearningproject.member.repository;

import com.event.msalearningproject.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity,Long> {

    Optional<MemberEntity> findByUserIdAndPhone(String userId, String phone);
}
