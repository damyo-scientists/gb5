package com.game.gb5.controller.user;

import com.game.gb5.model.user.User;
import com.game.gb5.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestParam("user_name") String userName,
                                       @RequestParam("user_id") String userId) {
        User user = userService.create(userName, userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") final long id) {
        User user = userService.getById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        User existedUser = userService.getById(user.getId());
        if (existedUser != null) {
            User changedUser = userService.update(user);
            return new ResponseEntity<>(changedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final long id) {
        User user = userService.getById(id);
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
