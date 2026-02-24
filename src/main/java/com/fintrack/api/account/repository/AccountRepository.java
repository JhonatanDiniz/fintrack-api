package com.fintrack.api.account.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.api.account.entity.Account;
import com.fintrack.api.user.entity.User;

public interface AccountRepository extends JpaRepository<Account, UUID> {
  
  boolean existsByUserAndName(User user, String name);

}
