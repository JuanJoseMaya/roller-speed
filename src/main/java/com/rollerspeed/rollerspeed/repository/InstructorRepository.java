package com.rollerspeed.rollerspeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
