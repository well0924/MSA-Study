package com.event.msalearningproject.message.controller;

import com.event.msalearningproject.message.dto.request.MessageRequest;
import com.event.msalearningproject.message.dto.response.MessageResponse;
import com.event.msalearningproject.message.service.MessageHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/message")
@Tag(name = "MessageHistory", description = "메시지 발송 및 이력 관리 API")
public class MessageRestController {

    private final MessageHistoryService messageHistoryService;

    @Operation(summary = "단건 메시지 발송", description = "회원 ID, 연락처, 메시지 타입에 따라 단건 메시지를 발송합니다.")
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageRequest request) {
        messageHistoryService.send(
                request.getMemberId(),
                request.getUserId(),
                request.getContents(),
                request.getType()
        );
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "다건 메시지 발송", description = "여러 건의 메시지를 한 번에 발송합니다.")
    @PostMapping("/send/bulk")
    public ResponseEntity<Void> sendBulkMessages(@RequestBody List<MessageRequest> requests) {
        messageHistoryService.sendBulk(requests);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 ID로 메시지 이력 조회", description = "회원 ID를 기준으로 메시지 발송 이력을 조회합니다.")
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<MessageResponse>> getMessageHistoryByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(messageHistoryService.findByMemberId(memberId));
    }

    @Operation(summary = "연락처로 메시지 이력 조회", description = "userId(연락처)를 기준으로 메시지 발송 이력을 조회합니다.")
    @GetMapping("/user")
    public ResponseEntity<List<MessageResponse>> getMessageHistoryByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(messageHistoryService.findByUserId(userId));
    }

    @Operation(summary = "회원 메시지 이력 삭제", description = "회원 탈퇴 시, 해당 회원의 메시지 이력을 삭제합니다.")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMessageHistory(@PathVariable Long memberId) {
        messageHistoryService.deleteByMemberId(memberId);
        return ResponseEntity.ok().build();
    }
}
