package com.thatsmyspot.commonlib.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventMetadata {
    private String eventId; // empty for events that are not stored, ID for already stored
//    private String aggregateId; // specified by DomainEvent definition by calling super(aggregateId)
//    private String version; // empty on creation, automatically specified by AggregateRoot when applied
//    private String aggregateType; // passed by AggregateRoot automatically as a part of apply() logic
    private LocalDateTime createdDate; // automatically specified on transport layer (broker publish)
    private String createdBy; // userId for authorized user, null for guest, appId from 3rd-party integrations.
                             // Passed on transport layer automatically
}
