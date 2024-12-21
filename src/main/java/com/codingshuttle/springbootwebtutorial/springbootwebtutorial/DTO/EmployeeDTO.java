package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name of the employee can't be empty")
    @Size(min = 5,max = 10,message = "no of char should be in the range:[3,10]")
    private String name;

    @Email(message = "Email should be a valid email")
    private String email;

    @Max(value = 80,message = "Age should be less than 80")
    @Min(value = 18,message = "Age should be more tha 18")
    private Integer age;

     @PastOrPresent(message = "date should be past or present")
     private LocalDate dateOfJoining;

     @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private boolean isActive;

}
