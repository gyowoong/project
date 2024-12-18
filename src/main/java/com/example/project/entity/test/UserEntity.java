package com.example.project.entity.test;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String addr;

    @Column(nullable = false)
    private String telNo;

    @Column(nullable = false)
    @Email
    private String email;

    private int point;

    private boolean reser;

    private int gender;
    @Column(nullable = false)
    private int age;

    private String brith;
}
