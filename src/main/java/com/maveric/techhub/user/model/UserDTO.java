package com.maveric.techhub.user.model;

import com.maveric.techhub.user.util.ServiceConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserDTO {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;
    private String email;
    private String designation;
    private String phoneNumber;
    private String bloodGroup;
    private String dateOfBirth;
    private String gender;
    private String primarySkillSet;
    private String secondarySkillSet;
}
