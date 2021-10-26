package com.programmers.heavenpay.member.service;

import com.programmers.heavenpay.error.exception.NotExistsException;
import com.programmers.heavenpay.member.dto.response.MemberFindResponse;
import com.programmers.heavenpay.member.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    Long useId = 1L;

    @BeforeAll
    void setup() {

    }

    @AfterAll
    void reset() {
        memberRepository.deleteAll();
    }

    @Test
    @Order(1)
    void create() {
        long beforeCount = memberRepository.count();

        memberService.create("ddkk94@naver.com", "Taid", "01011223344", "20211015", "남성");

        assertThat(beforeCount + 1, is(memberRepository.count()));
    }

    @Test
    @Order(2)
    void findById() {
        try {
            MemberFindResponse result = memberService.findById(useId);
            assertThat(result.getEmail(), is("ddkk94@naver.com"));
        } catch (NotExistsException e)
        {
            // todo: log.info 추가
        }
    }

    @Test
    @Order(3)
    void findAllByPages() {
        Page<MemberFindResponse> result = memberService.findAllByPages(Pageable.unpaged());
        assertThat(result.stream().count(), is(1L));
    }

    @Test
    @Order(4)
    void update() {
        memberService.update(useId, "jhum94@naver.com", "Taid22", "01011223344", "20211015", "남성");
        try {
            MemberFindResponse result = memberService.findById(useId);
            assertThat(result.getEmail(), is("jhum94@naver.com"));
            assertThat(result.getName(), is("Taid22"));
        } catch (NotExistsException e)
        {
            // todo: log.info 추가
        }
    }

    @Test
    @Order(5)
    void delete() {
        memberService.delete(useId);
        assertThat(memberRepository.count(), is(0L));
    }
}