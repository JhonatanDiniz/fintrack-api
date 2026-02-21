package com.fintrack.api.user.service;

import org.springframework.stereotype.Service;

import com.fintrack.api.user.dto.CreateUserRequest;
import com.fintrack.api.user.dto.UserResponse;
import com.fintrack.api.user.entity.User;
import com.fintrack.api.user.mapper.UserMapper;
import com.fintrack.api.user.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public UserResponse create(CreateUserRequest request){

    if (userRepository.existsByEmail(request.email())){
      throw new IllegalArgumentException("Usuário já cadastrado no sistema, e-mail: " + request.email());
    }

    User user = UserMapper.toEntity(request, request.password());
    User saved = userRepository.save(user);
    return UserMapper.toResponse(saved);
  }  
}
