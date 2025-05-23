package com.thatsmyspot.userservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "roles")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE roles SET deleted_date = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_date IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue( generator = "system-uuid")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "users")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<User> users;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Column(name = "deleted_date")
    @ColumnDefault("TIMESTAMP NULL")
    private LocalDateTime deletedDate;
}
