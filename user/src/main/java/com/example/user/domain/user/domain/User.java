package com.example.user.domain.user.domain;

import com.example.user.domain.user.domain.value.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false, unique = true)
    private String name;

    @Column(length = 50, nullable = false)
    private String password;

    @Email
    private String email;

    private Role role;

    @Builder(builderMethodName = "userCreateBuilder")
    public User(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Builder(builderMethodName = "userUpdateBuilder")
    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Builder(builderMethodName = "userPasswordUpdateBuilder")
    public User(String password) {
        this.password = password;
    }

}
