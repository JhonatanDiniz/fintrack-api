package com.fintrack.api.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fintrack.api.common.exception.EmailAlreadyExistsException;
import com.fintrack.api.common.exception.ResourceNotFoundException;
import com.fintrack.api.user.dto.CreateUserRequest;
import com.fintrack.api.user.dto.UpdateUserRequest;
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
      throw new EmailAlreadyExistsException("Usuário já cadastrado no sistema, e-mail: " + request.email());
    }
    User user = UserMapper.toEntity(request, request.password());
    User saved = userRepository.save(user);
    return UserMapper.toResponse(saved);
  }  

  public List<UserResponse> findAll(){
    List<User> users = userRepository.findAll();
    return users.stream()
      .map(UserMapper::toResponse)
      .toList();
  }

  public UserResponse findById(UUID id){
    return userRepository.findById(id)
      .map(UserMapper::toResponse).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id: " + id));
  }

  public UserResponse update(UUID id, UpdateUserRequest request){
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id: " + id));

    if (request.email() != null) {
      String newEmail = request.email().trim();
      if (!user.getEmail().equalsIgnoreCase(newEmail)) {
        if(userRepository.existsByEmail(newEmail)){
          throw new EmailAlreadyExistsException("Usuário já cadastrado no sistema, e-mail: " + request.email());
        }        
      }
      user.updateData(request.name(), request.email());
    } else{
      user.updateData(request.name(), null);
    } 
     
    User saved = userRepository.save(user);
    return UserMapper.toResponse(saved);
  }
}
