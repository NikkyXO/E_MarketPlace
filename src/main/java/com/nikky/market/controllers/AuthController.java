package com.nikky.market.controllers;

import java.io.IOException;
import java.net.URI;

import com.nikky.market.authentication.RegisterResponse;
import com.nikky.market.emails.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikky.market.authentication.AuthResponse;
import com.nikky.market.authentication.AuthenticationRequest;
import com.nikky.market.authentication.RegisterRequest;
import com.nikky.market.services.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService service;

  private final EmailService emailService;

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(
          @RequestBody RegisterRequest request
  ) {
    String Subject = "E-Commerce Application | Account Notification";
    String Message = "Hi " + request.getFirstName() + ", \n Your Account setup completed \n" +
            "WAIT FOR APPROVAL\n" + request.getEmail();

    emailService.sendEmail(request.getEmail(), Subject, Message);

    return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));

  }

  @PostMapping("/refresh-token")
  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

}