package com.devsuperior.bds01.controller;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> findAllPaged(Pageable pageable) {
        Page<EmployeeDTO> employeeDTOPage = employeeService.findAllPaged(pageable);
        return ResponseEntity.status(200).body(employeeDTOPage);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO = employeeService.createNewEmployee(employeeDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(employeeDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(employeeDTO);
    }
}
