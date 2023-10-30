package com.bits.assignment.group7.controller;

import com.bits.assignment.group7.dto.DepartmentDTO;
import com.bits.assignment.group7.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentService departmentService;

	@PostMapping("/")
	public DepartmentDTO add(@RequestBody DepartmentDTO department) {
		LOGGER.info("Department add: {}", department);
		return departmentService.save(department);
	}
	
	@GetMapping("/{id}")
	public DepartmentDTO findById(@PathVariable("id") Long id) {
		LOGGER.info("Department find: id={}", id);
		return departmentService.get(id);
	}
	
	@GetMapping("/")
	public List<DepartmentDTO> findAll() {
		LOGGER.info("Department find");
		return departmentService.getAll();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<DepartmentDTO> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		return departmentService.getByOrganizationId(organizationId);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable (value = "id") Long id) {
		this.departmentService.delete(id);
		return "Successfully deleted department ID:" + id;
	}

	@GetMapping("/health")
	public ResponseEntity<String> health() {
		return ResponseEntity.ok("Microservice 3: Department Service is up and running....!");
	}

}
