package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Repositories;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
