package org.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="users")
public class User extends BaseModel{
  private String name;
  private String email;
  private String password;
  @ManyToMany
  private List<Roles> roles;

}
