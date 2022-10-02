package com.example.protocolsrgr.controller;

import com.example.protocolsrgr.model.User;
import com.example.protocolsrgr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUsers(
            Map<String, Object> model
    ) {
        model.put("users", userRepository.findAll());
        return "user/user";
    }

    @PostMapping
    public String createUser(
            @RequestParam String name,
            @RequestParam String details
    ) {
        userRepository.save(new User(name, details));
        return "redirect:/user";
    }

    @GetMapping("{id}")
    public String getUser(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        userRepository.findById(id).ifPresent(user -> model.put("user", user));
        return "user/user_details";
    }

}
