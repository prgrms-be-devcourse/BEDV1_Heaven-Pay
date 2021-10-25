package com.programmers.heavenpay.member.converter;

import com.programmers.heavenpay.member.dto.request.MemberCreateRequest;
import com.programmers.heavenpay.member.dto.request.MemberUpdateRequest;
import com.programmers.heavenpay.member.dto.response.MemberFindResponse;
import com.programmers.heavenpay.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public Member toMemberCreateDto(String email, String name, String phoneNumber, String birth, String gender) {
        return Member.builder()
                .email(email)
                .name(name)
                .phoneNumber(phoneNumber)
                .birth(birth)
                .gender(gender)
                .build();
    }

    public MemberFindResponse toMemberFindDto(Member member) {
        return MemberFindResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .birth(member.getBirth())
                .gender(member.getGender())
                .build();
    }

    public Member toMemberUpdateDto(Member originMember, MemberUpdateRequest updateDto) {
        return Member.builder()
                .id(updateDto.getId())
                .email(updateDto.getEmail().isBlank() ? originMember.getEmail() : updateDto.getEmail())
                .name(updateDto.getName().isBlank() ? originMember.getName() : updateDto.getName())
                .phoneNumber(updateDto.getPhoneNumber().isBlank() ? originMember.getPhoneNumber() : updateDto.getPhoneNumber())
                .birth(updateDto.getBirth().isBlank() ? originMember.getBirth() : updateDto.getBirth())
                .gender(updateDto.getGender().isBlank() ? originMember.getGender() : updateDto.getGender())
                .build();
    }
}
