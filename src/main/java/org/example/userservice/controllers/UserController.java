package org.example.userservice.controllers;

import org.example.userservice.dtos.LoginRequestDto;
import org.example.userservice.dtos.LogoutRequestDto;
import org.example.userservice.dtos.SignUpRequestDto;
import org.example.userservice.dtos.UserDto;
import org.example.userservice.exceptions.UnauthorizedException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public Token login(@RequestBody LoginRequestDto loginRequestDto)
      throws UserNotFoundException, UnauthorizedException {
    return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

  }

  @PostMapping("/signup")
  public UserDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
    User user = userService.singUp(signUpRequestDto.getUsername(), signUpRequestDto.getPassword(),
        signUpRequestDto.getEmail());
    //convert User to USerDTO
    return UserDto.from(user);

  }

  @GetMapping("/logout")
  public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
    userService.logout(logoutRequestDto.getToken_value());
    return new ResponseEntity<>(HttpStatus.OK);

  }

  @GetMapping("/validate/{token_value}")
  public UserDto validateToken(@PathVariable String token_value) {
    User user = userService.validateToken(token_value);
    return UserDto.from(user);

  }


}
