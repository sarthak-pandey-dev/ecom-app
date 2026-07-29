package com.wishmedia.ecom_app;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping ("api/users")
@AllArgsConstructor
@RestController
public class UserController {


    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.find_all());
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("Added Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody User user) {
        boolean updated = userService.updateUser(id, user);
        if (updated) {
            return ResponseEntity.ok("User Updated Successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
