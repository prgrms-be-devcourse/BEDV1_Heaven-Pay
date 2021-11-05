package com.programmers.heavenpay.finance.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FinanceDetailResponse {
    private final Long id;
    private final String financeName;
    private final String financeType;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
}