package com.programmers.heavenpay.member.dto.response;

import com.programmers.heavenpay.annotation.ArbitraryAuthenticationPrincipal;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class MemberCreateResponse {
    @NotBlank(message = "아이디는 공백이면 안됩니다.")
    @ArbitraryAuthenticationPrincipal
    private final Long id;
}
