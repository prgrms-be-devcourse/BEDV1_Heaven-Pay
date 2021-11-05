package com.programmers.heavenpay.store.dto.response;

import com.programmers.heavenpay.store.entity.vo.StoreType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class StoreInfoResponse {
    private final Long id;

    private final String name;

    private final String type;

    private final String vendorCode;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;
}
