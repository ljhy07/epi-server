package com.example.user.domain.user.domain;

import com.example.user.domain.user.domain.value.LoginType;
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

    @Column(length = 50)
    private String password;

    @Email
    private String email;

    private String phone;

    private LoginType LoginType;

    private Role role;

    @Builder(builderMethodName = "userCreateBuilder")
    public User(String name, String password, String email, String phone, Role role, LoginType LoginType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.LoginType = LoginType;
        this.role = role;
    }

    @Builder(builderMethodName = "oauthUserCreateBuilder")
    public User(String name, String email, String phone, Role role, LoginType loginType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.LoginType = loginType;
        this.role = role;
    }

    @Builder(builderMethodName = "userUpdateBuilder")
    public User(String name, String email, String phone, Role role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    @Builder(builderMethodName = "userPasswordUpdateBuilder")
    public User(String password) {
        this.password = password;
    }

}
