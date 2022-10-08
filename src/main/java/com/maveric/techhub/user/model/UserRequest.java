package com.maveric.techhub.user.model;

import com.maveric.techhub.user.util.ServiceConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRequest {

    @NotBlank(message = ServiceConstants.FIRST_NAME_MANDATORY)
    private String firstName;
    private String middleName;
    @NotBlank(message = ServiceConstants.LAST_NAME_MANDATORY)
    private String lastName;
    @NotBlank(message = ServiceConstants.EMP_ID_IS_MANDATORY)
    private String employeeId;
    @Email(regexp = "[a-z0-9._%+-]+@maveric-systems.com",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = ServiceConstants.INVALID_EMAIL)
    private String email;
    private String designation;
    private String phoneNumber;
    private String bloodGroup;
    private String dateOfBirth;
    private String gender;
    private String primarySkillSet;
    private String secondarySkillSet;
}
