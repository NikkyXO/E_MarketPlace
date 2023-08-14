package com.nikky.market.authentication;


import com.nikky.market.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Role role;
  private String location;
}
