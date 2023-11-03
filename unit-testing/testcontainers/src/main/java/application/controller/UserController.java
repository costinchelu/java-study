package application.controller;

import application.model.UserDto;
import application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUser(@RequestParam String email){
        UserDto response = userService.findUserByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
