package com.event.msalearningproject.member.controller;

import com.event.msalearningproject.member.dto.request.MemberRequest;
import com.event.msalearningproject.member.dto.response.MemberResponse;
import com.event.msalearningproject.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원 API", description = "회원 등록, 조회, 삭제 관련 API입니다.")
@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberRestController {

    private final MemberService memberService;

    @Operation(summary = "전체 회원 조회", description = "가입된 전체 회원 목록을 조회합니다.")
    @GetMapping("/all")
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @Operation(summary = "회원 단건 조회", description = "userId와 phone으로 회원을 조회합니다.")
    @GetMapping
    public ResponseEntity<MemberResponse> findByUserIdAndPhone(
            @RequestParam("userId") String userId,
            @RequestParam("phone") String phone) {
        return ResponseEntity.ok(memberService.findByUserIdAndPhone(userId, phone));
    }

    @Operation(summary = "회원 가입", description = "회원가입 API입니다.")
    @PostMapping("/")
    public ResponseEntity<Long> createMember(@RequestBody MemberRequest request) {
        Long memberId  = memberService.createMember(request);
        return ResponseEntity
                .ok()
                .body(memberId);
    }

    @Operation(summary = "회원 삭제", description = "회원 ID로 회원을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
