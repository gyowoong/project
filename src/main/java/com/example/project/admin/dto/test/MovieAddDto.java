package com.example.project.admin.dto.test;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieAddDto {
    private Long tno;
    private String name;
    private String add;
    private String manager;

    private String state;

    public MovieAddDto(String name, String add, String state) {
        this.name = name;
        this.add = add;
        this.state = state;
    }

    public MovieAddDto(String name, String add, String manager, String state) {
        this.name = name;
        this.add = add;
        this.manager = manager;
        this.state = state;
    }

    
}
