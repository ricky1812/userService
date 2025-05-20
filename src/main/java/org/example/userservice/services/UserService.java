package org.example.userservice.services;

import org.example.userservice.exceptions.UnauthorizedException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;

public interface UserService {

  User singUp(String username, String password, String email);

  Token login(String email, String password) throws UserNotFoundException, UnauthorizedException;

  User validateToken(String tokenValue);

  void logout(String tokenValue);


}
