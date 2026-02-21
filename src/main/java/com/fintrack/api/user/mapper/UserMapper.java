package com.fintrack.api.user.mapper;

import java.time.LocalDateTime;

import com.fintrack.api.user.dto.CreateUserRequest;
import com.fintrack.api.user.dto.UserResponse;
import com.fintrack.api.user.entity.User;

public class UserMapper {
  
  private UserMapper(){}

  public static UserResponse toResponse(User user){
    return new UserResponse(
      user.getId(), 
      user.getName(), 
      user.getEmail(),
      user.getCreatedAt()
    );
  }

  public static User toEntity(CreateUserRequest request, String passwordHash){
    return User.builder()
      .name(request.name())
      .email(request.email())
      .passwordHash(passwordHash)
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }
}
