package com.event.msalearningproject.member.service;

import com.event.msalearningproject.config.exception.global.UserNotFoundException;
import com.event.msalearningproject.message.service.MessageHistoryService;
import com.event.msalearningproject.member.dto.request.MemberRequest;
import com.event.msalearningproject.member.dto.response.MemberResponse;
import com.event.msalearningproject.member.entity.MemberEntity;
import com.event.msalearningproject.member.repository.MemberEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final MessageHistoryService messageHistoryService;

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
        MemberEntity member = memberEntityRepository
                .findByUserIdAndPhone(userId,phone)
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
        //메시지 전송.
        messageHistoryService.send(memberId, member.getUserId(), "WELCOME "+member.getUserId(),member.getMessageType());
        return memberId;
    }

    public void deleteMember(Long id) {
        log.info("memberId::" + id);
        if(!memberEntityRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        //메시지 이력 삭제
        messageHistoryService.deleteByMemberId(id);
        //회원 삭제
        memberEntityRepository.deleteById(id);
    }
}
