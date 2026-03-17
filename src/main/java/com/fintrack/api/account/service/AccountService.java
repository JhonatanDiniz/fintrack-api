package com.fintrack.api.account.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fintrack.api.account.dto.AccountResponse;
import com.fintrack.api.account.dto.CreateAccountRequest;
import com.fintrack.api.account.entity.Account;
import com.fintrack.api.account.mapper.AccountMapper;
import com.fintrack.api.account.repository.AccountRepository;
import com.fintrack.api.common.exception.AccountAlreadyExistsException;
import com.fintrack.api.common.exception.ResourceNotFoundException;
import com.fintrack.api.user.entity.User;
import com.fintrack.api.user.repository.UserRepository;

@Service
public class AccountService {
  
  private final UserRepository userRepository;
  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository, UserRepository userRepository){
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
  }

  public AccountResponse create(CreateAccountRequest request){
    User user = userRepository.findById(request.userId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não existe."));
    if (accountRepository.existsByUserAndName(user, request.name())){
      throw new AccountAlreadyExistsException("Conta já existente com esse nome para o usuário informado.");
    }
    Account account = AccountMapper.toEntity(request, user);
    Account saved = accountRepository.save(account);
    return AccountMapper.toResponse(saved);
  }

  public List<AccountResponse> findAll(){
    List<Account> accounts = accountRepository.findAll();
    return accounts.stream()
      .map(AccountMapper::toResponse)
      .toList();
  }

  public AccountResponse findById(UUID id){
    return accountRepository.findById(id)
      .map(AccountMapper::toResponse).orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada."));
  }

}
