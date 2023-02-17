package com.dtotest.dto.service.dto;

import com.dtotest.dto.entity.AccountSettings;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    @JsonIgnoreProperties({"id"})
    private AccountSettings accountSettings;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private Date birthDate;
    private String permission;
    private Date startedWorking;
    private Integer experience;
    private boolean enabled;
    private boolean active;
    private boolean deletedGlobally;
    private boolean deletedInOrganization;
    private boolean verified;
    private String seniority;
    private String userCategory;
}
