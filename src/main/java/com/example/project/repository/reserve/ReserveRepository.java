package com.example.project.repository.reserve;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.reserve.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

}
