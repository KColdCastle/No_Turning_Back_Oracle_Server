package NoTurningBack.jinddobey.controller;


import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



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
    public List<Member> memberInfo(@PathVariable String email){
        return service.informationAll(email);
    }

    @GetMapping("memberList")
    public List<Member> memberList(){
        return service.memberList();
    }
}
