package com.thatsmyspot.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventContext {
    private String userId;
    private String appId;
    private String tenantId;
    private String correlationId;
}
