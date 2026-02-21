package com.fintrack.api.user.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fintrack.api.user.dto.CreateUserRequest;
import com.fintrack.api.user.dto.UserResponse;
import com.fintrack.api.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  
  private final UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest request){
    UserResponse user = userService.create(request);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.id()).toUri();
    return ResponseEntity.created(uri).body(user);
  }
}
