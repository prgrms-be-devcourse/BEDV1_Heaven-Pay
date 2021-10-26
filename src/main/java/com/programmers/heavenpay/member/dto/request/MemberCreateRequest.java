package com.programmers.heavenpay.member.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberCreateRequest {
    @NotBlank(message = "email should not be blank")
    @Email
    private String email;

    @Pattern(regexp="^[ㄱ-ㅎ|가-힣|a-z|A-Z|]+$", message="Invalid name address!")
    private String name;

    @Pattern(regexp="[0-9]{3}[0-9]{3,4}[0-9]{4}", message="Invalid phoneNumber address!")
    private String phoneNumber;

    @Pattern(regexp="[0-9]{8}", message="Invalid birth address!")
    private String birth;

    @NotBlank(message = "gender should not be blank")
    private String gender;
}
