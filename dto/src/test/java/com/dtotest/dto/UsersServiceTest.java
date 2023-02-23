package com.dtotest.dto;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.interfaces.UsersService;
import com.dtotest.dto.dao.CountryRepo;
import com.dtotest.dto.dao.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private CountryRepo countryRepo;

    @InjectMocks
    private UsersService usersService;

    private Users user;
    private LocalDateTime currentDate;

    @Before
    public void setup() {

        user = new Users();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("Password1");

        currentDate = LocalDateTime.now();
    }

    @Test
    public void testAddNewUser_Successful() {
        when(userRepo.findUserByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepo.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(countryRepo.findCountry("Croatia")).thenReturn(1);
        when(countryRepo.findById(1)).thenReturn(Optional.of(new Country(1, "Croatia")));

        usersService.addNewUser(user, "Croatia");

        assertNotNull(user.getCreatedAt());
        assertEquals("Croatian", user.getAccountSettings().getLanguage());
        assertEquals("Europe/Zagreb", user.getAccountSettings().getTimezone());

        verify(userRepo, times(1)).save(user);

        assertThrows(IllegalStateException.class, () -> {
            user.setPassword("invalidpass");

            usersService.addNewUser(user, "Croatia");
        });
        }
    @Test(expected = IllegalStateException.class)
    public void testAddNewUser_UsernameTaken() {
        when(userRepo.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));

        usersService.addNewUser(user, "Croatia");
    }

    @Test(expected = IllegalStateException.class)
    public void testAddNewUser_EmailTaken() {
        when(userRepo.findUserByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepo.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        usersService.addNewUser(user, "Croatia");
    }

    @Test(expected = IllegalStateException.class)
    public void testAddNewUser_InvalidEmail() {
        user.setEmail("notanemail@address.");

        usersService.addNewUser(user, "Croatia");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testAddNewUser_WrongCountry() {
        usersService.addNewUser(user, "Croatia");
    }

    /*@Test
    public void testUpdateUsers() {
        // Create a new user and save it in the database
        Users user1 = new Users("johndoe", "barnaulti@gmail.com", "John", "Doe", "William", "male", new Date(), "password1", "senior", "category1", "picture1");
        *//*user1.setUsername("johndoe");
        user1.setEmail("barnaulti@bloglines.com");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setMiddleName("William");
        user1.setGender("male");
        user1.setBirthDate(new Date());
        user1.setPassword("password1");
        user1.setSeniority("senior");
        user1.setUserCategory("category1");
        user1.setProfilePicture("picture1");*//*
        user1 = userRepo.save(user1);

        // Update the user's username, email, and password
        String newUsername = "janedoe";
        String newEmail = "janedoe@example.com";
        String newPassword = "newpassword1";
        usersService.updateUsers(1, newUsername, newEmail, null, null, null, null, null, newPassword, null, null, null);
        // Verify that the user's username, email, and password have been updated
        Users updatedUser = userRepo.findById(1).get();
        assertEquals(newUsername, updatedUser.getUsername());
        assertEquals(newEmail, updatedUser.getEmail());
        assertTrue(BCrypt.checkpw(newPassword, updatedUser.getPassword()));

        // Attempt to update the user's username to a name that is already taken
        assertThrows(IllegalStateException.class, () -> usersService.updateUsers(1, "johndoe", null, null, null, null, null, null, null, null, null, null));

        // Attempt to update the user's password to a weak password
        assertThrows(IllegalStateException.class, () -> usersService.updateUsers(1, null, null, null, null, null, null, null, "weak", null, null, null));

        // Attempt to update the user's password to a password that has been used recently
        String oldPassword = user1.getPassword();
        usersService.updateUsers(user1.getId(), null, null, null, null, null, null, null, "newpassword1", null, null, null);
        assertThrows(IllegalArgumentException.class, () -> usersService.updateUsers(1, null, null, null, null, null, null, null, oldPassword, null, null, null));

        // Attempt to update the user's password with the same password
        assertThrows(IllegalArgumentException.class, () -> usersService.updateUsers(1, null, null, null, null, null, null, null, "newpassword1", null, null, null));

        // Attempt to update the user's birth date to a future date
        assertThrows(IllegalArgumentException.class, () -> usersService.updateUsers(1, null, null, null, null, null, null, new Date(System.currentTimeMillis() + 86400000), null, null, null, null));
    }*/
}
