package com.zhichengchen.readwrite2.controller;

import java.util.List;

import com.zhichengchen.readwrite2.domain.User;
import com.zhichengchen.readwrite2.domain.UserService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class DetailsController {

    private final UserService userService;
    @GetMapping(value="/users/all" , produces=MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(){
        return userService.getAllUserDetails();
    }

    @DeleteMapping(value="/users" , produces=MediaType.APPLICATION_JSON_VALUE)
    public String getAllEmployeeDetails(@RequestParam("id") long id){
        userService.deleteUser(id);
        return "Deleted";
    }
}
