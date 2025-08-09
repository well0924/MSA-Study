package com.event.member.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class MemberDeleteEvent extends ApplicationEvent {

    private Long id;

    public MemberDeleteEvent(Object source,Long memberId) {
        super(source);
        this.id = memberId;
    }
}
