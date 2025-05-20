package org.example.userservice.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.Roles;
import org.example.userservice.models.User;

@Getter
@Setter
public class UserDto {

  private String username;
  private String email;
  private List<String> roles;

  public static UserDto from(User user) {
    if (user == null) {
      return null;
    }
    UserDto userDto = new UserDto();
    userDto.setUsername(user.getName());
    userDto.setEmail(user.getEmail());
    userDto.setRoles(new ArrayList<>());
    for(Roles roles: user.getRoles())
      userDto.getRoles().add(roles.getValue());
    return userDto;

  }

}
