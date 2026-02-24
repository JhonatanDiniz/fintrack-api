package com.fintrack.api.account.dto;

import java.util.UUID;

public record CreateAccountRequest(
  UUID userId,
  String name
) {}
