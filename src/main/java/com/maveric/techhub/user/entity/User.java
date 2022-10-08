package com.maveric.techhub.user.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "User_Info")
public class User {
    @Id
    @Column
    private String id;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;
    @Column
    private String employeeId;
    @Column
    private String email;
    @Column
    private String designation;
    @Column
    private String phoneNumber;
    @Column
    private String bloodGroup;
    @Column
    private String gender;
    @Column
    private String primarySkillSet;
    @Column
    private String secondarySkillSet;
}
