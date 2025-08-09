package com.event.member.service;

import com.event.common.exception.global.UserNotFoundException;
import com.event.member.dto.request.MemberRequest;
import com.event.member.dto.response.MemberResponse;
import com.event.member.entity.MemberEntity;
import com.event.member.event.MemberCreateEvent;
import com.event.member.event.MemberDeleteEvent;
import com.event.member.repository.MemberEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class MemberService {

    private final MemberEntityRepository memberEntityRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        return memberEntityRepository
                .findAll()
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberResponse findByUserIdAndPhone(String userId,String phone) {
        MemberEntity member = memberEntityRepository.findByUserIdAndPhone(userId,phone)
                .orElseThrow(()-> new UserNotFoundException());
        return MemberResponse.from(member);
    }

    public Long createMember(MemberRequest memberRequest) {
        log.info("MemberRequest::" + memberRequest.toString());

        MemberEntity member = MemberEntity
                .builder()
                .userId(memberRequest.getUserId())
                .password(memberRequest.getPassword())
                .phone(memberRequest.getPhone())
                .messageType(memberRequest.getMessageType())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
        log.info("MemberEntity::" + member);

        Long memberId = memberEntityRepository.save(member).getId();

        //이벤트 발행
        applicationEventPublisher.publishEvent(new MemberCreateEvent(
                this, // 이벤트 소스
                member.getId(),
                member.getUserId(),
                member.getMessageType(),
                "WELCOME " + member.getUserId() // 메시지 내용을 이벤트에 담아 보냄
        ));
        return memberId;
    }

    public void deleteMember(Long id) {
        log.info("memberId::" + id);
        if(!memberEntityRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        //이벤트 발행
        applicationEventPublisher.publishEvent(new MemberDeleteEvent(this,id));
        //회원 삭제
        memberEntityRepository.deleteById(id);
    }
}
