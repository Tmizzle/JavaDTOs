package com.dtotest.dto.Controller;

import com.dtotest.dto.service.dto.UserDTO;
import com.dtotest.dto.service.interfaces.UsersService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                                      @RequestParam(required = false) String gender)
                                      /*@RequestParam(required = false) String birthDateStr) throws ParseException {
        if (birthDateStr != null && !birthDateStr.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(birthDateStr);*/
    {
        usersService.updateUsers(Id, username, email, firstName, lastName, middleName, gender);
    }
}
