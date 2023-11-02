package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", maxAge = 3600)
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    // ! 회원가입
    @PostMapping("admin_join")
    public void adminJoin(@RequestBody Admin admin) {// JSON 파일로만 입력 하도록 하였음.
        adminService.join(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> adminLogin(@RequestBody Admin admin, HttpSession session,
                                                          HttpServletResponse response) {
        Admin authenticatedAdmin = adminService.login(admin.getAdminId(), admin.getAdminPassword());

        if (authenticatedAdmin != null) {
            // 세션에 로그인 정보 저장
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("adminId", authenticatedAdmin.getAdminId());

            String employeeName = adminService.getEmployeeNameByAdminId(authenticatedAdmin.getAdminId());
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "로그인 성공");
            responseMap.put("employeeName", employeeName);

            // 로그인 성공 후 쿠키 설정
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60 * 60); // 예: 1시간 (60초 * 60분)
            cookie.setSecure(true); // HTTPS에서만 쿠키 전송
            cookie.setHttpOnly(true); // JavaScript에서 접근 불가
            response.addCookie(cookie);

            return ResponseEntity.ok(responseMap);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "로그인 실패");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/checkLoginStatus")
    public ResponseEntity<Map<String, Object>> checkLoginStatus(HttpServletRequest request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    response.put("loggedIn", true);
                    response.put("adminId", session.getAttribute("adminId"));
                    return ResponseEntity.ok(response);
                }
            }
        }
        response.put("loggedIn", false);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session, HttpServletResponse response) {
        session.invalidate(); // 세션 종료
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0); // 쿠키를 즉시 삭제
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/suspendedMembers")
    public List<Member> getSuspendedMembers() {
        return adminService.getSuspendedMembers();
    }

    @GetMapping("memberList")
    public List<Member> memberList() {
        return adminService.memberList();
    }

    @GetMapping("blackMemberList")
    public List<Member> blackMemberList() {
        return adminService.blackMemberListS();
    }

    @PutMapping("blackMemberList") // 대충 블랙리스트 추가한다는 내용
    public void blackMemberAdd() {

    }
}
