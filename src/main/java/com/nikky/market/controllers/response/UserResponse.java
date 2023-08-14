package com.nikky.market.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikky.market.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("data")
	private User user;

}