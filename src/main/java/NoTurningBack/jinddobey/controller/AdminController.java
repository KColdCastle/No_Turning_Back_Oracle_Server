package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("admin_join")//회원가입
    public void adminJoin(@RequestBody Admin admin){//JSON 파일로만 입력 하도록 하였음.
        adminService.join(admin);
    }

    @GetMapping("memberList")
    public List<Member> memberList(){
        return adminService.memberList();
    }

    @GetMapping("blackMemberList")
    public List<Member> blackMemberList(){
        return adminService.blackMemberListS();
    }

    @PutMapping("blackMemberList")//대충 블랙리스트 추가한다는 내용
    public void blackMemberAdd(){

    }
}
