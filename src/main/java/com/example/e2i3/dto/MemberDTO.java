package com.example.e2i3.dto;

import com.example.e2i3.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String password;

    @Builder
    public MemberDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Builder
    public MemberDTO(String email, String password){
        this.email = email;
        this.password=password;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }

}
