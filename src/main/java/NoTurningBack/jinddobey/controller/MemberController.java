package NoTurningBack.jinddobey.controller;


import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("member")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class MemberController {
    @Autowired
    MemberService service;

    @PostMapping("member_join")//회원가입
    public void memberJoin(@RequestBody Member member){//JSON 파일로만 입력 하도록 하였음.
        service.join(member);
    }

    @GetMapping("member_info/{email}")
    public Member memberInfo(@PathVariable String email){
        return service.informationAll(email);
    }

    @PutMapping("/member_info/{email}")//사용자 정보 변경 요청 컨트롤러
    public String memberUpdate(@RequestBody Member member, @PathVariable String email){
        service.update(member, email);
        return "redirect:member/member_join";
    }



    @PostMapping("/login") // 로그인 세션
    public ResponseEntity<Map<String, Object>> memberLogin(@RequestBody Member member, HttpServletRequest request) {

        // 서비스를 통해 로그인 시도
        Member loggedInMember = service.login(member.getEmail(), member.getPassword());


        if (loggedInMember != null) {
            // 로그인 성공 시 세션 생성
            HttpSession session = request.getSession();
            session.setAttribute("loggedInMember", loggedInMember);

            // 성공 메시지와 세션 정보를 JSON으로 응답합니다.
            Map<String, Object> response = new HashMap<>();
            response.put("message", "로그인 성공");
            response.put("sessionInfo", loggedInMember);

            return ResponseEntity.ok(response);
        } else {
            // 로그인 실패 시 401 Unauthorized 상태 코드와 실패 메시지를 반환합니다.
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "로그인 실패");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}
