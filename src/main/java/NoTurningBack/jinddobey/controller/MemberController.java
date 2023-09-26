package NoTurningBack.jinddobey.controller;


import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.domain.Transaction;
import NoTurningBack.jinddobey.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("member")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class MemberController {
    @Autowired
    MemberService service;

    @PostMapping("member_join")
    public void MemberJoin(@RequestBody Member member){
        service.join(member);
    }

    @GetMapping("member_info/{email}")
    public List<Member> MemberInfo(@PathVariable String email){
        return service.informationAll(email);
    }
}
