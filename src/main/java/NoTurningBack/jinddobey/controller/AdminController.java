package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class AdminController {
    @Autowired
    MemberService memberService;

    @GetMapping("memberList")
    public List<Member> memberList(){
        return memberService.memberList();
    }

    @GetMapping("blackMemberList")
    public List<Member> blackMemberList(){
        return memberService.blackMemberListS();
    }

    @PutMapping("blackMemberList")//대충 블랙리스트 추가한다는 내용
    public void blackMemberAdd(){

    }
}
