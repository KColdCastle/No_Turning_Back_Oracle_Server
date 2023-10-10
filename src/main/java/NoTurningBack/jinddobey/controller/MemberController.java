package NoTurningBack.jinddobey.controller;


import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static NoTurningBack.jinddobey.service.MemberLoginConst.YES_ID_PWD;


@RequestMapping("member")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class MemberController {
    @Autowired
    MemberService service;

    @PostMapping("member_join")
    public void memberJoin(@RequestBody Member member){//JSON 파일로만 입력 하도록 하였음.
        service.join(member);
    }

    @GetMapping("member_info/{email}")
    public Member memberInfo(@PathVariable String email){
        return service.informationAll(email);
    }

    @GetMapping("memberList")
    public List<Member> memberList(){
        return service.memberList();
    }

    @PostMapping("login")
    public String tryLogin(@RequestBody Member member, HttpSession session){
        System.out.println("이메일:"+member.getEmail());
        int result = service.check(member.getEmail(), member.getPassword());
        if(result == YES_ID_PWD){
            Member loginOkUser = service.getLogin(member.getEmail());
            session.setAttribute("loginOkUser", loginOkUser);
            return "성공";
        }
        else {
            return "개같이 실패";
        }
    }
    @GetMapping("logout.do")
    public void logout(HttpSession session){
        session.invalidate();//session 모든객체 제거
    }
}
