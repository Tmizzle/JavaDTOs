package com.dtotest.dto.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private String username;
    private String email;
    private AccountSettingsDTO accountSettings;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String permission;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startedWorking;
    private Integer experience;
    private boolean enabled;
    private boolean active;
    private boolean deletedGlobally;
    private boolean deletedInOrganization;
    private boolean verified;
    private String seniority;
    private String userCategory;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
}
