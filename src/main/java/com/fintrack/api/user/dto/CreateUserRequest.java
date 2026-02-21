package com.fintrack.api.user.dto;

public record CreateUserRequest(
  String name,
  String email,
  String password
) {}
