package com.example.project.admin.Entity;

import com.example.project.admin.Entity.constant.AdminRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "movieState")
@Getter
@Setter
@Entity(name = "THEATER")
@Table(name = "THEATER")
public class MovieAdd {
    @Id
    @Column(name = "THEATER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tno;

    @Column(name = "THEATER_NAME")
    private String name;

    @Column(name = "THEATER_ADD")
    private String add;

    private String manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    private MovieState movieState;
}
