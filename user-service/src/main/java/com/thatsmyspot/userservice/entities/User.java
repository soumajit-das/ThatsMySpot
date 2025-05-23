package com.thatsmyspot.userservice.entities;

import com.thatsmyspot.userservice.infrastructure.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE users SET deleted_date = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_date IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue( generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", type = org.hibernate.id.UUIDGenerator.class)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "roles")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("ACTIVE")
    private UserStatus status;

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
