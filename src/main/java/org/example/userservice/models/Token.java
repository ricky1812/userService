package org.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tokens")
public class Token extends BaseModel{
  private String value;
  private Date expiryDt;
  @ManyToOne
  private User user;

}
