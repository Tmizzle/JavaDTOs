package com.dtotest.dto.Controller;

import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.UserDTO;
import com.dtotest.dto.service.interfaces.UsersService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return usersService.getUsers();
    }

    @GetMapping(path = "{id}")
    public UserDTO getUserById(@PathVariable("id") Integer id){
        return usersService.getUserById(id);
    }

    @PutMapping(path = "{id}")
    public void updateAccountSettings(@PathVariable("id") Integer Id,
                                      @RequestParam(required = false) String username,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) String firstName,
                                      @RequestParam(required = false) String lastName,
                                      @RequestParam(required = false) String middleName,
                                      @RequestParam(required = false) String gender,
                                      @RequestParam(required = false) String password,
                                      @RequestParam(required = false) String birthDateStr){
        if(birthDateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            LocalDateTime birthDateTime = birthDate.atStartOfDay();
            Date date = Date.from(birthDateTime.atZone(ZoneId.systemDefault()).toInstant());
            usersService.updateUsers(Id, username, email, firstName, lastName, middleName, gender, date, password);
        } else {
            usersService.updateUsers(Id, username, email, firstName, lastName, middleName, gender, null, password);
        }
}
    @PostMapping
    public void addNewUser(@RequestBody Users user, @RequestParam String country){
        usersService.addNewUser(user, country);
    }
}
