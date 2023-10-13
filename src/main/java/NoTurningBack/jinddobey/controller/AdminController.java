package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin")
@CrossOrigin(origins = "*", maxAge = 3600) // #매우 중요!
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("admin_join") // 회원가입
    public void adminJoin(@RequestBody Admin admin) {// JSON 파일로만 입력 하도록 하였음.
        adminService.join(admin);
    }

    @GetMapping("memberList")
    public List<Member> memberList() {
        return adminService.memberList();
    }

    @GetMapping("blackMemberList")
    public List<Member> blackMemberList() {
        return adminService.blackMemberListS();
    }

    @PostMapping("/login") // 로그인 세션
    public ResponseEntity<String> memberLogin(@RequestBody Admin admin, HttpServletRequest request) {

        // 서비스를 통해 로그인 시도
        boolean loginState = adminService.login(admin.getAdminId(), admin.getAdminPassword());

        if (loginState != false) {
            // 로그인 성공 시 세션 생성
            // HttpSession session = request.getSession();
            // session.setAttribute("loggedInMember", loggedInMember);

            // 성공 메시지와 세션 정보를 JSON으로 응답합니다.
            // Map<String, Object> response = new HashMap<>();
            // response.put("message", "로그인 성공");
            // response.put("sessionInfo", loggedInMember);

            return ResponseEntity.ok("로그인 성공");
        } else {
            // 로그인 실패 시 401 Unauthorized 상태 코드와 실패 메시지를 반환합니다.
            // Map<String, Object> errorResponse = new HashMap<>();
            // errorResponse.put("error", "로그인 실패");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("로그인 실패");
        }
    }

    @PutMapping("blackMemberList") // 대충 블랙리스트 추가한다는 내용
    public void blackMemberAdd() {

    }
}
