package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.AdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("admin")
@CrossOrigin(origins = "*", maxAge = 3600) // #매우 중요!
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final String SECRET_KEY = "whdudxo_WkdchlrhdnfxmfktbvjQkdnj"; // 실제로는 더 강력한 키를 사용하세요.

    private String getCookieValue(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @PostMapping("admin_join") // 회원가입
    public void adminJoin(@RequestBody Admin admin) {// JSON 파일로만 입력 하도록 하였음.
        adminService.join(admin);
    }

    // ! set JWT_SECRET_KEY=whdudxoWkdchlrhdnfxmfktbvjQkdnj (((씨끄맀 끼) <- 존나중요 노출금지)

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> adminLogin(@RequestBody Admin admin, HttpServletResponse response) {
        boolean loginState = adminService.login(admin.getAdminId(), admin.getAdminPassword());

        if (loginState) {
            String employeeName = adminService.getEmployeeNameByAdminId(admin.getAdminId());
            String jwtToken = Jwts.builder()
                    .setSubject(admin.getAdminId())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();

            // JWT 토큰을 쿠키로 전송
            Cookie authCookie = new Cookie("AUTH_TOKEN", jwtToken);
            authCookie.setHttpOnly(true); // JS에서 쿠키에 접근 못하게 함
            authCookie.setPath("/"); // 애플리케이션의 모든 경로에서 유효
            response.addCookie(authCookie);

            // 응답 데이터 설정 및 반환
            Map<String, String> responseData = new HashMap<>();
            responseData.put("message", "로그인 성공");
            responseData.put("employeeName", employeeName);
            return ResponseEntity.ok(responseData);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "로그인 실패");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
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
