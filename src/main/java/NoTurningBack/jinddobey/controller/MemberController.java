package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RequestMapping("member")
@CrossOrigin(origins = "*", maxAge = 3600) // #매우 중요!
@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("member_join") // 회원가입
    public ResponseEntity<String> memberJoin(@RequestBody Member member) {// JSON 파일로만 입력 하도록 하였음.
        System.out.println("회원가입 넘어온 데이터: " + member);
        boolean joinState = memberService.join(member);
        if (joinState != false) {
            return ResponseEntity.ok("회원가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원가입 실패");
        }
    }

    @GetMapping("member_info/{email}")
    public Member memberInfo(@PathVariable String email) {
        return memberService.informationAll(email);
    }

    @PutMapping("/member_info/{email}") // 사용자 정보 변경 요청 컨트롤러
    public String memberUpdate(@RequestBody Member member, @PathVariable String email) {
        memberService.update(member, email);
        return "redirect:member/member_join";
    }

    @PostMapping("/login") // 로그인 세션
    public ResponseEntity<String> memberLogin(@RequestBody Member member, HttpServletRequest request) {
        System.out.println("로그인 정보 :" + member);
        // 서비스를 통해 로그인 시도
        boolean loginState = memberService.login(member.getEmail(), member.getPassword());
        System.out.println(member);

        if (loginState != false) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("로그인 실패");
        }
    }

    @GetMapping("member/{email}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable String email) {
        Optional<Member> optionalMember = memberService.findMemberByEmail(email);
        return optionalMember.map(member -> new ResponseEntity<>(member, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("increaseWarning/{email}")
    public ResponseEntity<Void> increaseWarning(@PathVariable String email) {
        memberService.increaseWarningS(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("minusWarning/{email}")
    public void minusWarningS(@PathVariable String email) {
        memberService.minusWarningS(email);
    }

    @GetMapping("memberState")
    public List<Member> memberState() {
        return memberService.memberState(true);
    }

    @GetMapping("memberStateFalse")
    public ResponseEntity<List<Member>> getMembersByStateFalse() {
        List<Member> members = memberService.findMembersByStateFalse();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // ! 유저 검색
    @PostMapping("memberSearchEmail")
    public List<Member> memberSearchEmail(@RequestBody Member member) {
        List<Member> list = memberService.getListByEmail(member);
        return list;
    }

    // ! 유저 검색
    @PostMapping("memberSearchPN")
    public List<Member> memberSearchPN(@RequestBody Member member) {
        List<Member> list = memberService.getListByPhoneNum(member);
        return list;
    }
}
