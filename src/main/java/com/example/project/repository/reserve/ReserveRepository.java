package com.example.project.repository.reserve;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

}
