package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Repositories.EmployeeRepository;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/secretMessage")
    public String getSecretMessage() {
        return "This is my Secret Message";
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(employeeId);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new NoSuchElementException("Employee not found"));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
        EmployeeDTO savedEmployee=employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeByiD(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return  ResponseEntity.ok(employeeService.updateEmployeeByiD(employeeId,employeeDTO));
    }

    @DeleteMapping("/{employeeId}")
    public String deleteById(@PathVariable Long employeeId){
      return  employeeService.deleteById(employeeId);
    }

    @PatchMapping("/{employeeId}")
    public EmployeeDTO updatePartialEmployee(@RequestBody Map<String, Objects> updates,
                                             @PathVariable Long employeeId){
    return  employeeService.updatePartialEmployee(employeeId,updates);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}