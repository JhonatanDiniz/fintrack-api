package com.fintrack.api.account.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fintrack.api.account.dto.AccountResponse;
import com.fintrack.api.account.dto.CreateAccountRequest;
import com.fintrack.api.account.entity.Account;
import com.fintrack.api.user.entity.User;

public class AccountMapper {
  
  private AccountMapper(){}

  public static AccountResponse toResponse(Account account){
    return new AccountResponse(
     account.getId(),
     account.getUser().getId(),
     account.getName(),
     account.getCreatedAt()
     );
  }

  public static Account toEntity(CreateAccountRequest request, User user){
    return Account.builder()
      .id(UUID.randomUUID())
      .user(user)
      .name(request.name())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }
}
