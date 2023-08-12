package com.nikky.market.authentication;


import com.nikky.market.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


  private String username;
  private String email;
  private String password;
  private Role role;
}
