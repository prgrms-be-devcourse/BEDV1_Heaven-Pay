package com.programmers.heavenpay.member.repository;

import com.programmers.heavenpay.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Override
    List<Member> findAll();

    @Override
    List<Member> findAllById(Iterable<Long> longs);

    @Override
    <S extends Member> S save(S entity);
}
