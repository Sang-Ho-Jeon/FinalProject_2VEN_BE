package com.sysmatic2.finalbe.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class SignupDTO {

    // 회원타입 형식 : TRADER 또는 INVESTOR 둘 중 하나
    @Pattern(regexp = "^(TRADER|INVESTOR)$", message = "회원 유형은 'INVESTOR' 또는 'TRADER' 여야 합니다.")
    private String memberType;

    // email 형식 확인
    @Email(message = "이메일 형식에 맞게 입력되어야 합니다.")
    private String email;

    // 비밀번호 형식 : 영문 숫자를 포함한 8~10자
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$",
            message = "비밀번호는 공백 없이 영문과 숫자를 하나 이상씩 포함한 6~10자의 문자여야 합니다."
    )
    private String password;

    private String confirmPassword;

    // nickname 형식 : 2~10자의 문자열 (특수문자 X)
    @Pattern(
            regexp = "^[A-Za-z\\d]{2,10}$",
            message = "닉네임은 2~10자 이내의 문자여야 합니다."
    )
    private String nickname;

    // 휴대폰번호 형식 : 숫자 only, 10~11자
    @Pattern(
            regexp = "^\\d{10,11}$",
            message = "휴대폰번호는 '-' 없이 10~11개의 숫자여야 합니다."
    )
    private String phoneNumber;
}
