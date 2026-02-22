package com.fintrack.api.common.exception;

public class EmailAlreadyExistsException extends RuntimeException {
  public EmailAlreadyExistsException(String msg){
    super(msg);
  }  
}
