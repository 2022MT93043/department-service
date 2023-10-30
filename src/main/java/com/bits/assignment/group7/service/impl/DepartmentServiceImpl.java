package com.bits.assignment.group7.service.impl;

import com.bits.assignment.group7.dto.DepartmentDTO;
import com.bits.assignment.group7.repository.DepartmentRepository;
import com.bits.assignment.group7.service.DepartmentService;
import com.bits.assignment.group7.entity.Department;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO save(DepartmentDTO dto) {
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);
        department = departmentRepository.save(department);
        BeanUtils.copyProperties(department, dto);
        return dto;
    }

    @Override
    public DepartmentDTO get(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).get();
        DepartmentDTO dto = new DepartmentDTO();
        BeanUtils.copyProperties(department, dto);
        return dto;
    }

    @Override
    public List<DepartmentDTO> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(entity -> new DepartmentDTO(entity.getId(), entity.getOrganizationId(), entity.getName(), entity.getCode(), entity.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<DepartmentDTO> getByOrganizationId(Long departmentId) {
        List<Department> departments =  departmentRepository.findByOrganizationId(departmentId);
        return departments.stream()
                .map(entity -> new DepartmentDTO(entity.getId(), entity.getOrganizationId(), entity.getName(), entity.getCode(), entity.getAddress()))
                .collect(Collectors.toList());
    }
}