package com.sysmatic2.finalbe.member.controller;

import com.sysmatic2.finalbe.member.dto.SignupDTO;
import com.sysmatic2.finalbe.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    //로그인
    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }
    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();

        return "logout";
    }

    //이메일찾기
    @GetMapping("/check-phone")
    public String checkPhone(HttpServletRequest request) {
        return "check-phone";
    }

    //이메일 체크(중복체크) & 이메일 인증 코드 전송
    @GetMapping("/check-email")
    public String checkEmail(HttpServletRequest request) {
        return "check-email";
    }

    //비밀번호 재설정
    @PostMapping("/password")
    public String password(HttpServletRequest request) {
        return "password";
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@Valid @RequestBody SignupDTO signupDTO) {

        memberService.signUp(signupDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "status", "success",
                "message", "회원가입에 성공했습니다."
        ));
    }

    //닉네임 중복 체크
    @GetMapping("/check-nickname")
    public ResponseEntity<Map<String, String>> checkNickname(
            @Pattern(
                regexp = "^[A-Za-z\\d]{2,10}$",
                message = "닉네임은 2~10자 이내의 문자여야 합니다."
            )
            String nickname) {

        memberService.duplicateNicknameCheck(nickname);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "사용 가능한 닉네임입니다."
        ));
    }

    //회원탈퇴
    @DeleteMapping("/withdrawal")
    public String delete(HttpServletRequest request) {
        return "withdrawal";
    }

    //개인정보상세조회
    @GetMapping("/details")
    public String details(HttpServletRequest request) {
        return "details";
    }
    //회원정보수정
    @PutMapping("/details")
    public String updateDetails(HttpServletRequest request) {
        return "updateDetails";
    }
    //비밀번호 확인
    @PostMapping("/check-password")
    public String checkPassword(HttpServletRequest request) {
        return "check-password";
    }
    //비밀번호 변경
    @PatchMapping("/change-password")
    public String changePassword(HttpServletRequest request) {
        return "change-password";
    }
}
