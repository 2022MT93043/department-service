package com.bits.assignment.group7.service;

import com.bits.assignment.group7.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO save(DepartmentDTO department);

    DepartmentDTO get(Long departmentId);

    void delete(Long id);

    List<DepartmentDTO> getAll();

    List<DepartmentDTO> getByOrganizationId(Long departmentId);

}