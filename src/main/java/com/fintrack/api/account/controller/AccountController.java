package com.fintrack.api.account.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fintrack.api.account.dto.AccountResponse;
import com.fintrack.api.account.dto.CreateAccountRequest;
import com.fintrack.api.account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService){
    this.accountService = accountService;
  }

  @PostMapping
  public ResponseEntity<AccountResponse> create(@RequestBody CreateAccountRequest request){
    AccountResponse account = accountService.create(request);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.id()).toUri();
    return ResponseEntity.created(uri).body(account);
  }

  @GetMapping
  public ResponseEntity<List<AccountResponse>> findAll(){
    List<AccountResponse> accounts = accountService.findAll();
    return ResponseEntity.ok(accounts);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AccountResponse> findById(@PathVariable UUID id){
    AccountResponse account = accountService.findById(id);
    return ResponseEntity.ok(account);
  }
}
