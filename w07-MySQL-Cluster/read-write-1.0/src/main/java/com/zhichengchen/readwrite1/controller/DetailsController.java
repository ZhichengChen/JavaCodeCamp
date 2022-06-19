package com.zhichengchen.readwrite1.controller;

import java.util.List;

import com.zhichengchen.readwrite1.datasource.DataSourceContextHolder;
import com.zhichengchen.readwrite1.datasource.DataSourceEnum;
import com.zhichengchen.readwrite1.domain.User;
import com.zhichengchen.readwrite1.domain.UserService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DetailsController {
    private final UserService userService;
    private final DataSourceContextHolder dataSourceContextHolder;
    @GetMapping(value="/users/all" , produces=MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(){
        dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_READ);
        return userService.getAllUserDetails();
    }

    @DeleteMapping(value="/users" , produces=MediaType.APPLICATION_JSON_VALUE)
    public String getAllEmployeeDetails(@RequestParam("id") long id){
        dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_WRITE);
        userService.deleteUser(id);
        return "Deleted";
    }
}
