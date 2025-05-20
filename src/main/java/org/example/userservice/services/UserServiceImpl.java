package org.example.userservice.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.example.userservice.exceptions.UnauthorizedException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.TokenRepository;
import org.example.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;

  public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository) {
    this.userRepository = userRepository;
    this.tokenRepository=tokenRepository;
  }

  @Override
  public User singUp(String username, String password, String email) {
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if(optionalUser.isPresent()){
      //redirect to login;
      return optionalUser.get();
    }
    User user=new User();
    user.setEmail(email);
    user.setName(username);
    //toDo:we should save password using Bcrypt generator
    user.setPassword(password);
    return userRepository.save(user);

  }

  @Override
  public Token login(String email, String password)
      throws UserNotFoundException, UnauthorizedException {
    Optional<User> optionalUser=userRepository.findByEmail(email);
    if(optionalUser.isEmpty()){
      throw new UserNotFoundException("user with email "+email+" doesnt exists");
    }
    User user= optionalUser.get();
    //check for password
    if(user.getPassword().equals(password)){
      Token token=new Token();
      token.setUser(user);
      token.setValue(String.valueOf(UUID.randomUUID()));
      Date currentDate=new Date();
      Calendar calendar=Calendar.getInstance();
      calendar.setTime(currentDate);
      calendar.add(Calendar.DAY_OF_MONTH,30);
      Date dateAfter30days=calendar.getTime();
      token.setExpiryDt(dateAfter30days);
      return tokenRepository.save(token);
    }
    //login failed
    throw new UnauthorizedException("Login is not valid");
  }

  @Override
  public User validateToken(String tokenValue) {
    return null;
  }

  @Override
  public void logout(String tokenValue) {
    Optional<Token> optionalToken=tokenRepository.findByValue(tokenValue);
    if(optionalToken.isEmpty())
      throw new RuntimeException("Token is invalid");
    Token token= optionalToken.get();
    token.setDeleted(true);
    tokenRepository.save(token);


  }
}
