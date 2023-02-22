package com.dtotest.dto.service.interfaces;


import com.dtotest.dto.dao.CountryRepo;
import com.dtotest.dto.dao.UserRepo;
import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.UserDTO;
import com.dtotest.dto.service.mapper.UsersMapper;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Data
public class UsersService {

    private final UserRepo userRepo;
    private final UsersMapper usersMapper;
    private final CountryRepo countryRepo;
    LocalDateTime currentDateTime = LocalDateTime.now();
    Date currentDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());

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
                            Date date,
                            String password/*,
                            String seniority,
                            String userCategory,
                            String profilePicture*/
    ) {
        Users users = userRepo.findById(Id).orElseThrow(() -> new IllegalStateException("" +
                "account setting with id " + Id + " does not exist"));

        if (username != null && username.length() > 0 && !Objects.equals(users.getUsername(), username)) {
            users.setUsername(username);
            users.setUpdatedAt(currentDate);
        }
        if (email != null && email.length() > 0 && !Objects.equals(users.getEmail(), email)) {
            users.setEmail(email);
            users.setUpdatedAt(currentDate);
        }
        if (firstName != null && firstName.length() > 0 && !Objects.equals(users.getFirstName(), firstName)) {
            users.setFirstName(firstName);
            users.setUpdatedAt(currentDate);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(users.getLastName(), lastName)) {
            users.setLastName(lastName);
            users.setUpdatedAt(currentDate);
        }
        if (middleName != null && middleName.length() > 0 && !Objects.equals(users.getMiddleName(), middleName)) {
            users.setMiddleName(middleName);
            users.setUpdatedAt(currentDate);
        }
        if (gender != null && gender.length() > 0 && !Objects.equals(users.getGender(), gender)) {
            users.setGender(gender);
            users.setUpdatedAt(currentDate);
        }
        if (date != null && !Objects.equals(users.getBirthDate(), date)) {
            users.setBirthDate(date);
            users.setUpdatedAt(currentDate);
        }
        if (password != null) {
            if (!Objects.equals(users.getPassword(), password)) {
                if (!Objects.equals(users.getOldPassword(), password)) {
                    String oldpw = users.getPassword();
                    users.setOldPassword(oldpw);
                    users.setPassword(password);
                    users.setUpdatedAt(currentDate);
                }
                else throw new IllegalArgumentException("You have used that password in recent past");
            }
            else throw new IllegalArgumentException("You can't use same password");
        }
    }
    public void addNewUser(Users users, String country) {
        String emailRegex = "^(?!\\.)[a-zA-Z0-9._%+-]+(?!\\.|\\.{2,})[^.]@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(users.getEmail()).matches() || users.getEmail().isEmpty()) {
            throw new IllegalStateException("Email invalid");
        }
        Optional<Users> emailCheck = userRepo.findUserByEmail(users.getEmail());
        Integer countryId = countryRepo.findCountry(country);
        if (emailCheck.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        Country defaultCountry = countryRepo.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country with that ID not found"));

        AccountSettings accountSettings = new AccountSettings(defaultCountry);
        switch(country) {
            case "Croatia":
                accountSettings.setLanguage("Croatian");
                accountSettings.setTimezone("Europe/Zagreb");
                break;
            case "Serbia":
                accountSettings.setLanguage("Serbian");
                accountSettings.setTimezone("Europe/Beograd");
                break;
            case "Bosnia and Herzegovina":
                accountSettings.setLanguage("Bosnian");
                accountSettings.setTimezone("Europe/Sarajevo");
            default:
                throw new IllegalArgumentException("Wrong input!");
        }
        Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z]).{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(users.getPassword());
        if (!passwordMatcher.matches() || users.getPassword().isEmpty()) {
            throw new IllegalStateException("Password must be at least 8 chars long, contain at least 1 number and 1 letter");
        }
        users.setAccountSettings(accountSettings);
        users.setCreatedAt(currentDate);
        userRepo.save(users);
    }
}
