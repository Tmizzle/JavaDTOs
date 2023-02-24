package com.dtotest.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
public class Users {

    @Id
    @SequenceGenerator(
            name = "new_user_sequence",
            sequenceName = "new_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "new_user_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
    )
    private Integer id;
    @OneToOne(targetEntity = AccountSettings.class, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_account_settings",
            referencedColumnName = "id",
            nullable = false
    )
    private AccountSettings accountSettings;
    private String username;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "middle_name",
            nullable = false
    )
    private String middleName;
    private String gender;
    @Column(
            name = "birth_date",
            nullable = false
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String password;
    @Column(
            name = "old_password"
    )
    private String oldPassword;
    private String permission;
    @Column(
            name = "started_working",
            nullable = false
    )
    private Date startedWorking;
    private Integer experience;
    private boolean enabled;
    private boolean active;
    @Column(
            name = "deleted_globally"
    )
    private boolean deletedGlobally;
    @Column(
            name = "deleted_in_organization"
    )
    private boolean deletedInOrganization;
    private boolean verified;
    private String seniority;
    @Column(
            name = "user_category"
    )
    private String userCategory;
    @Column(
            name = "updated_at"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    @Column(
            name = "id_updated_by"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Integer idUpdatedBy;
    @Column(
            name = "created_at"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @Column(
            name = "profile_picture"
    )
    private String profilePicture;
    @Column(
            name = "created_by"
    )
    private Integer createdBy;
    @Column(
            name = "deleted"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deleted;
    @Column(
            name = "id_deleted_by"
    )
    private Integer idDeletedBy;

    public Users(String username, String email, String firstName, String lastName, String middleName, String gender, Date birthDate, String password, String seniority, String userCategory, String profilePicture) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.password = password;
        this.seniority = seniority;
        this.userCategory = userCategory;
        this.profilePicture = profilePicture;
    }
}
