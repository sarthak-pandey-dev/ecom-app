package com.wishmedia.ecom_app.controller;

import com.wishmedia.ecom_app.dto.UserRequest;
import com.wishmedia.ecom_app.dto.UserResponse;
import com.wishmedia.ecom_app.service.UserService;
import com.wishmedia.ecom_app.model.User;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping ("api/users")
@AllArgsConstructor
@RestController
public class UserController {


    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponse>> fetchAll() {
        return new ResponseEntity<>(userService.find_all(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody UserRequest user) {
        userService.addUser(user);
        return ResponseEntity.ok("Added Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        boolean updated = userService.updateUser(id, userRequest);
        if (updated) {
            return ResponseEntity.ok("User Updated Successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
