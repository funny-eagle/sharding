package com.redocon.shardingjdbc.controller;

import com.redocon.shardingjdbc.entity.User;
import com.redocon.shardingjdbc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add/{city}/{name}")
    public String addUser(@PathVariable("city") String city, @PathVariable("name") String name){
        userMapper.addUser(city, name);
        return "add user complete!";
    }

    @GetMapping("/list")
    public List<User> listUser(){
        return userMapper.list();
    }


    @GetMapping("/customer/{name}")
    public Map customer(@PathVariable("name") String name){
        return userMapper.findCustomer(name);
    }
}

