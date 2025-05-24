package org.example.userservice.security.services;

import org.example.userservice.models.Roles;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
  private Roles roles;
  public CustomGrantedAuthority(Roles roles){
    this.roles=roles;
  }

  @Override
  public String getAuthority() {
    return roles.getValue();
  }
}
