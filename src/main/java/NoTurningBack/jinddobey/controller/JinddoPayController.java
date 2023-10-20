package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.service.JinddoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("jinddoPay")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class JinddoPayController {

    @Autowired
    JinddoPayService jinddoPayService;

    @GetMapping("/{email}")
    public ResponseEntity<Long> jinddoPayBalanceCheck(@PathVariable String email){
        System.out.println("검색한 유저 Email: "+email);
        long jinddoBalance = jinddoPayService.jinddoPayBalance(email);
        return ResponseEntity.ok(jinddoBalance);
    }

    @PostMapping("/create")//사용자 정보 변경 요청 컨트롤러
    public ResponseEntity<String> jinddoPayCreate(@RequestBody Balance balance){
        System.out.println("받아온 유저 Email: "+balance.getEmail());
        boolean jinddoStart=jinddoPayService.jinddoPayCreateS(balance);
        System.out.println("jinddoStart"+jinddoStart);
        if(jinddoStart != false){
            return ResponseEntity.ok("진또페이 성공");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("진또 사용 실패");
        }
    }

    @PutMapping("/charge/{email}") // 사용자 정보 변경 요청 컨트롤러
    public ResponseEntity<String> jinddoPayCharge(@RequestBody Balance balance, @PathVariable String email) {
        jinddoPayService.jinddoPayChargeS(balance, email);
        return ResponseEntity.ok("진또페이 성공");
    }
}
