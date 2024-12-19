package com.example.project.admin.Entity;


import com.example.project.admin.Entity.constant.AdminRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "THEATER_STATE")
public class MovieState {
    @Id
    @Column(name = "STATE_ID")
    private Long sno;

    @Column(name = "STATE")
    private String state;

}
