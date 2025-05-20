package org.example.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="roles")
public class Roles extends BaseModel{
  private String value;


}
