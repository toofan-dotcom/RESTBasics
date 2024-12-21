package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper mapper;

    public Optional<EmployeeDTO> getEmployeeById(Long id){
//        EmployeeEntity employeeEntity=employeeRepository.findById(id).orElse(null);
//       return mapper.map(employeeEntity, EmployeeDTO.class);
        return employeeRepository.findById(id).map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities =employeeRepository.findAll();
       return employeeEntities
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity= mapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployee= employeeRepository.save(toSaveEntity);
        return mapper.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeByiD(Long employeeId,EmployeeDTO employeeDTO) {
         EmployeeEntity employeeEntity= mapper.map(employeeDTO,EmployeeEntity.class);
         employeeEntity.setId(employeeId);
         EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
         return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public String deleteById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "Deleted";

    }
    public boolean isExistByEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public EmployeeDTO updatePartialEmployee(Long employeeId,
                                             Map<String, Objects> updates) {
    boolean exists=isExistByEmployeeId(employeeId);
    if(!exists)
        return null;
    EmployeeEntity employeeEntity=employeeRepository.findById(employeeId)
            .get();
    updates.forEach((field,value)->{
        Field fieldToBeUpdated= ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
        fieldToBeUpdated.setAccessible(true);
        ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
    });
    return mapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
