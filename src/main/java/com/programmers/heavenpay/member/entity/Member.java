package com.programmers.heavenpay.member.entity;

import com.programmers.heavenpay.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_phone_number")
    private String phoneNumber;

    @Column(name = "member_birth")
    private String birth;

    @Column(name = "member_gender")
    private String gender;

    public void changeValues(String email, String name, String phoneNumber, String birth, String gender) {
        this.email = email.isBlank() ? this.email : email;
        this.name = name.isBlank() ? this.name : name;
        this.phoneNumber = phoneNumber.isBlank() ? this.phoneNumber : phoneNumber;
        this.birth = birth.isBlank() ? this.birth : birth;
        this.gender = gender.isBlank() ? this.gender : gender;
    }
}