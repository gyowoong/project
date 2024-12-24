package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Inquiry {

    private String counselingType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 필수 항목입니다.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "문의 내용은 필수 항목입니다.")
    @Column(nullable = false)
    private String content;

    public String getCounselingType() {
        return counselingType;
    }

<<<<<<< HEAD
    public void setCounselingType(String counselingType) {
        this.counselingType = counselingType;
=======
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
>>>>>>> 560831b48a02e4345a2370324d58d0c8cdf674ab
    }

}
