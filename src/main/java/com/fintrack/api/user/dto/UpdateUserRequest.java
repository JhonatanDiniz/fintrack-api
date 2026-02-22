package com.fintrack.api.user.dto;

public record UpdateUserRequest(
  String name,
  String email
) {}
