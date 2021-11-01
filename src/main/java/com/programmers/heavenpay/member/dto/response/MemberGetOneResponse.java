package com.programmers.heavenpay.member.dto.response;

import com.programmers.heavenpay.annotation.ArbitraryAuthenticationPrincipal;
import com.programmers.heavenpay.member.entity.vo.GenderType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class MemberGetOneResponse {
    @NotBlank(message = "아이디는 공백이면 안됩니다.")
    @ArbitraryAuthenticationPrincipal
    private final Long id;

    @NotBlank(message = "이메일은 공백이면 안됩니다")
    private final String email;

    @NotBlank(message = "이름은 공백이면 안됩니다")
    private final String name;

    @NotBlank(message = "전화번호는 공백이면 안됩니다")
    private final String phoneNumber;

    @NotBlank(message = "생년월일은 공백이면 안됩니다")
    private final String birth;

    @NotBlank(message = "성별은 공백이면 안됩니다")
    private final GenderType gender;
}
