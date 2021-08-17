package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
