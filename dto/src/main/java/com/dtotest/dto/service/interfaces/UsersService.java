package com.dtotest.dto.service.interfaces;


import com.dtotest.dto.dao.UserRepo;
import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.AccountSettingsDTO;
import com.dtotest.dto.service.dto.UserDTO;
import com.dtotest.dto.service.mapper.AccountSettingsMapper;
import com.dtotest.dto.service.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UsersService {

    private final UserRepo userRepo;
    private final UsersMapper usersMapper;

    public UsersService(UserRepo userRepo, UsersMapper usersMapper) {
        this.userRepo = userRepo;
        this.usersMapper = usersMapper;
    }

    public List<UserDTO> getUsers() {
        List<Users> users = userRepo.findAll();
        List<UserDTO> userDTOs = usersMapper.entitiesToDTOs(users);
        return userDTOs;
    }


        public UserDTO getUserById(Integer id){
        Users users = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("" +
                "account setting with id " + id + " does not exist"));
        return usersMapper.entityToDTO(users);
    }
    @Transactional
    public void updateUsers(Integer Id,
                            String username,
                            String email,
                            String firstName,
                            String lastName,
                            String middleName,
                            String gender,
                            Date date
    ) {
        Users users = userRepo.findById(Id).orElseThrow(() -> new IllegalStateException("" +
                "account setting with id " + Id + " does not exist"));

        if (username != null && username.length() > 0 && !Objects.equals(users.getUsername(), username)) {
            users.setUsername(username);
        }
        if (email != null && email.length() > 0 && !Objects.equals(users.getEmail(), email)) {
            users.setEmail(email);
        }
        if (firstName != null && firstName.length() > 0 && !Objects.equals(users.getFirstName(), firstName)) {
            users.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(users.getLastName(), lastName)) {
            users.setLastName(lastName);
        }
        if (middleName != null && middleName.length() > 0 && !Objects.equals(users.getMiddleName(), middleName)) {
            users.setMiddleName(middleName);
        }
        if (gender != null && gender.length() > 0 && !Objects.equals(users.getGender(), gender)) {
            users.setGender(gender);
        }
        if (date != null && !Objects.equals(users.getBirthDate(), date)) {
            users.setBirthDate(date);
        }
    }
}
