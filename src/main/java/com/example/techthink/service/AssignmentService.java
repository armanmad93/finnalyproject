package com.example.techthink.service;

import com.example.techthink.persistence.Assignment;
import com.example.techthink.persistence.CourseSection;
import com.example.techthink.service.DTO.AssignmentDTO;
import com.example.techthink.service.DTO.CourseSectionDTO;
import com.example.techthink.service.DTO.SubCategoryDTO;

import java.util.List;

public interface AssignmentService {
    Assignment create(AssignmentDTO request);

    Assignment readById(Long id);

    List<Assignment> readAll();

    Assignment update(Long id, AssignmentDTO request);

    Boolean delete(Long id);
}
