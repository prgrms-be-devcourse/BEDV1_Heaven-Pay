package com.programmers.heavenpay.member.dto.response;

import com.programmers.heavenpay.annotation.ArbitraryAuthenticationPrincipal;
import com.programmers.heavenpay.member.entity.vo.GenderType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class MemberFindResponse {
    @NotBlank(message = "id should not be blank")
    @ArbitraryAuthenticationPrincipal
    private final Long id;

    @NotBlank(message = "email should not be blank")
    private final String email;

    @NotBlank(message = "name should not be blank")
    private final String name;

    @NotBlank(message = "phoneNumber should not be blank")
    private final String phoneNumber;

    @NotBlank(message = "birth should not be blank")
    private final String birth;

    @NotBlank(message = "gender should not be blank")
    private final GenderType gender;
}
