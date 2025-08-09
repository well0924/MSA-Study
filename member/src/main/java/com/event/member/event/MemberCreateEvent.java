package com.event.member.event;

import com.event.common.enums.MessageType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class MemberCreateEvent extends ApplicationEvent {

    private Long memberId;
    private String userId;
    private MessageType messageType;
    private String messageContent;


    public MemberCreateEvent(Object source, Long memberId, String userId, MessageType messageType, String messageContent) {
        super(source);
        this.memberId = memberId;
        this.userId = userId;
        this.messageType = messageType;
        this.messageContent = messageContent;
    }
}
