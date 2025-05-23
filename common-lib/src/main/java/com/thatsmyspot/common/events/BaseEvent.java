package com.thatsmyspot.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseEvent {
    private Object payload;
    private Exception exception;
    private EventMetadata metadata;
    private EventContext context;
}
