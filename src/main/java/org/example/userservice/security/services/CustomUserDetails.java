package org.example.userservice.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.example.userservice.models.Roles;
import org.example.userservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private final String username;
  private final String password;
  private final boolean accountNotExpired;
  private final boolean accountNotLocked;
  private final boolean credentialNotExpired;
  private final boolean enabled;
  private List<GrantedAuthority> authorities;


  public CustomUserDetails(User user) {
    this.username = user.getName();
    this.password = user.getPassword();
    this.accountNotExpired = true;
    this.accountNotLocked = true;
    this.credentialNotExpired = true;
    this.enabled = true;
    authorities = new ArrayList<>();
    for (Roles roles : user.getRoles()) {
      authorities.add(new CustomGrantedAuthority(roles));

    }


  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNotExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNotLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialNotExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
