package com.dtotest.dto.service.interfaces;


import com.dtotest.dto.dao.CountryRepo;
import com.dtotest.dto.dao.UserRepo;
import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.UserDTO;
import com.dtotest.dto.service.mapper.UsersMapper;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
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

    // get current date and timestamp
    LocalDateTime currentDateTime = LocalDateTime.now();
    Date currentDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());

    // Retrieves all users
    public List<UserDTO> getUsers() {
        List<Users> users = userRepo.findAll();
        List<UserDTO> userDTOs = usersMapper.entitiesToDTOs(users);
        return userDTOs;
    }
    // Retrieves a user by his ID
    public UserDTO getUserById(Integer id){
        Users users = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("" +
                "user with id " + id + " does not exist"));
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
                            String password,
                            String seniority,
                            String userCategory,
                            String profilePicture
    ) {

        Users users = userRepo.findById(Id).orElseThrow(() -> new IllegalStateException("" +
                "user with id " + Id + " does not exist"));
        // Update username if it has changed
        if (username != null && username.length() > 0 && !Objects.equals(users.getUsername(), username)) {
            // check if username is taken
            Optional<Users> usernameCheck = userRepo.findUserByUsername(username);
            if (usernameCheck.isPresent()){
                throw new IllegalStateException("Username taken");
            }
            users.setUsername(username);
            users.setUpdatedAt(currentDate);
        }
        // Update email if it has changed
        if (email != null && email.length() > 0 && !Objects.equals(users.getEmail(), email)) {
            users.setEmail(email);
            users.setUpdatedAt(currentDate);
        }
        // Update firstName if it has changed
        if (firstName != null && firstName.length() > 0 && !Objects.equals(users.getFirstName(), firstName)) {
            users.setFirstName(firstName);
            users.setUpdatedAt(currentDate);
        }
        // Update lastName if it has changed
        if (lastName != null && lastName.length() > 0 && !Objects.equals(users.getLastName(), lastName)) {
            users.setLastName(lastName);
            users.setUpdatedAt(currentDate);
        }
        // Update middleName if it has changed
        if (middleName != null && middleName.length() > 0 && !Objects.equals(users.getMiddleName(), middleName)) {
            users.setMiddleName(middleName);
            users.setUpdatedAt(currentDate);
        }
        // Update gender if it has changed
        if (gender != null && gender.length() > 0 && !Objects.equals(users.getGender(), gender)) {
            users.setGender(gender);
            users.setUpdatedAt(currentDate);
        }
        // Update BirthDate if it has changed
        if (date != null && !Objects.equals(users.getBirthDate(), date)) {
            users.setBirthDate(date);
            users.setUpdatedAt(currentDate);
        }
        // Update password if it has changed
        if (password != null) {
            if (!Objects.equals(users.getPassword(), password)) {
                if (!Objects.equals(users.getOldPassword(), password)) {
                    // Check that the new password meets the required format
                    Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z]).{8,}$");
                    Matcher passwordMatcher = passwordPattern.matcher(password);
                    if (!passwordMatcher.matches()) {
                        throw new IllegalStateException("Password must be at least 8 chars long, contain at least 1 number and 1 letter");
                    }
                    // Set the old password to the current password and hash the new password
                    users.setOldPassword(users.getPassword());
                    String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
                    users.setPassword(hashedPassword);
                    users.setUpdatedAt(currentDate);
                }
                else throw new IllegalArgumentException("You have used that password in recent past");
            }
            else throw new IllegalArgumentException("You can't use same password");
        }
        // Update seniority if it has changed
        if (seniority != null && seniority.length() > 0 && !Objects.equals(users.getSeniority(), seniority)) {
            users.setSeniority(seniority);
            users.setUpdatedAt(currentDate);
        }

        // Update user category if it has changed
        if (userCategory != null && userCategory.length() > 0 && !Objects.equals(users.getUserCategory(), userCategory)) {
            users.setUserCategory(userCategory);
            users.setUpdatedAt(currentDate);
        }
        // Update profile picture if it has changed
        if (profilePicture != null && profilePicture.length() > 0 && !Objects.equals(users.getProfilePicture(), profilePicture)) {
            users.setProfilePicture(profilePicture);
            users.setUpdatedAt(currentDate);
        }
    }
    public void addNewUser(Users users, String country) {
        // check if username is taken
        Optional<Users> usernameCheck = userRepo.findUserByUsername(users.getUsername());
        if (usernameCheck.isPresent()){
            throw new IllegalStateException("Username taken");
        }
        Optional<Users> emailCheck = userRepo.findUserByEmail(users.getEmail());
        // check if email is already taken
        if (emailCheck.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        // regular expression pattern for email validation
        String emailRegex = "^(?!\\.)[a-zA-Z0-9._%+-]+(?!\\.|\\.{2,})[^.]@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        // check if email is valid
        if (!pattern.matcher(users.getEmail()).matches() || users.getEmail().isEmpty()) {
            throw new IllegalStateException("Email invalid");
        }
        // find country ID based on input string
        Integer countryId = countryRepo.findCountry(country);
        Country defaultCountry = countryRepo.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country with that ID not found"));

        AccountSettings accountSettings = new AccountSettings(defaultCountry);
        // create new account settings object based on country and set language and timezone
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
        // regular expression pattern for password validation
        Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z]).{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(users.getPassword());
        // check if password is valid
        if (!passwordMatcher.matches()) {
            throw new IllegalStateException("Password must be at least 8 chars long, contain at least 1 number and 1 letter");
        }
        // hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
        users.setPassword(hashedPassword);
        // set account settings, creation date, and save user object to repository
        users.setAccountSettings(accountSettings);
        users.setCreatedAt(currentDate);
        userRepo.save(users);
    }
}
