package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

}
