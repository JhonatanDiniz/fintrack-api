package com.fintrack.api.common.exception;

public class AccountAlreadyExistsException extends RuntimeException {
  public AccountAlreadyExistsException(String msg){
    super(msg);
  }
}
