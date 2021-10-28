package com.programmers.heavenpay.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmers.heavenpay.member.dto.request.MemberCreateRequest;
import com.programmers.heavenpay.member.dto.request.MemberUpdateRequest;
import com.programmers.heavenpay.member.dto.response.MemberCreateResponse;
import com.programmers.heavenpay.member.entity.vo.GenderType;
import com.programmers.heavenpay.member.repository.MemberRepository;
import com.programmers.heavenpay.member.service.MemberService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    private ObjectMapper objectMapper;

    @Autowired
    MemberController memberController;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        memberRepository.deleteAll();
    }

    @AfterEach
    void exit() {
        memberRepository.deleteAll();
    }

    @Test
    void signUpTest() throws Exception {
        // Given
        MemberCreateRequest request = MemberCreateRequest.builder()
                .email("ddkk94@naver.com")
                .name("Taid")
                .phoneNumber("00011112222")
                .birth("19991122")
                .gender(GenderType.MALE.getTypeStr())
                .build();

        // When
        MockHttpServletRequestBuilder requestBuilder = post("/api/v1/members");
        requestBuilder.content(objectMapper.writeValueAsString(request));
        requestBuilder.contentType(MediaTypes.HAL_JSON_VALUE);
        requestBuilder.accept(MediaTypes.HAL_JSON_VALUE);

        // Then
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void editInfo() throws Exception {
        // Given
        MemberCreateResponse response = memberService.create("ddkk94@naver.com", "Taid1111", "00011112222", "19991122", GenderType.MALE.getTypeStr());
        MemberUpdateRequest request = MemberUpdateRequest.builder()
                .email("test@naver.com")
                .name("Taid2222")
                .phoneNumber("00011112222")
                .birth("19991122")
                .gender(GenderType.MALE.getTypeStr())
                .build();

        // When
        MockHttpServletRequestBuilder requestBuilder = patch("/api/v1/members/{memberId}", response.getId());
        requestBuilder.content(objectMapper.writeValueAsString(request));
        requestBuilder.contentType(MediaTypes.HAL_JSON_VALUE);
        requestBuilder.accept(MediaTypes.HAL_JSON_VALUE);

        // Then
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        // Given
        MemberCreateResponse response = memberService.create("ddkk94@naver.com", "Taid1111", "00011112222", "19991122", GenderType.MALE.getTypeStr());

        // When
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = delete("/api/v1/members/{memberId}", response.getId());
        mockHttpServletRequestBuilder.contentType(MediaTypes.HAL_JSON_VALUE);
        mockHttpServletRequestBuilder.accept(MediaTypes.HAL_JSON_VALUE);


        // Then
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }
}