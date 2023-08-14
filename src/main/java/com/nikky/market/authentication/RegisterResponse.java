package com.nikky.market.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikky.market.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {


    @JsonProperty("created_user")
    private User created_user;
}
