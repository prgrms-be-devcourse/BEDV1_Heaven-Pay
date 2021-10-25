package com.programmers.heavenpay.member.service;

import com.programmers.heavenpay.member.converter.MemberConverter;
import com.programmers.heavenpay.member.dto.response.MemberFindResponse;
import com.programmers.heavenpay.member.entity.Member;
import com.programmers.heavenpay.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberConverter converter;

    @Transactional
    public Long create(String email, String name, String phoneNumber, String birth, String gender) {
        Member member = converter.toMemberCreateDto(email, name, phoneNumber, birth, gender);
        Member result = memberRepository.save(member);

        return result.getId();
    }

    @Transactional(readOnly = true)
    public MemberFindResponse findById(Long id) throws Exception {
        return memberRepository.findById(id)
                .map(converter::toMemberFindDto)
                .orElseThrow(() -> new Exception("findById: member_id(" + id + ") not found"));
    }

    @Transactional(readOnly = true)
    public Page<MemberFindResponse> findAllByPages(Pageable pageable){
        return memberRepository.findAll(pageable)
                .map(converter::toMemberFindDto);
    }

    @Transactional
    public void update(Long id, String email, String name, String phoneNumber, String birth, String gender) throws Exception {
        Member originMember = memberRepository.findById(id)
                .orElseThrow(() -> new Exception("updateMember: member_id(" + id + ") not found"));

        originMember.changeValues(email, name, phoneNumber, birth, gender);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new Exception("deleteMember: member_id(" + id + ") not found"));

        memberRepository.delete(member);
    }
}