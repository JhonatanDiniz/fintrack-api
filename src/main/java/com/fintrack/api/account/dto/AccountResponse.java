package com.fintrack.api.account.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponse(
  UUID id,
  UUID userId,
  String name, 
  LocalDateTime createdAt
) {}
