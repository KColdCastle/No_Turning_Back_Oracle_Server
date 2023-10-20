package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.dto.BalanceCheckDto;
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

    @GetMapping("/create/{email}")
    public ResponseEntity<BalanceCheckDto> jinddoPayBalanceCheck(@PathVariable String email){
        System.out.println("검색한 유저 Email: "+email);
        BalanceCheckDto jinddoBalance = jinddoPayService.jinddoPayBalance(email);
        System.out.println("가져온 계좌값:"+jinddoBalance.getBalance());
        if(jinddoBalance == null) {
            return ResponseEntity.noContent().build();
            //404로 리턴
        }else{
            return ResponseEntity.ok(jinddoBalance);
            //200으로 리턴
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> jinddoPayCreate(@RequestBody Balance balance){
        System.out.println("받아온 유저 Email: "+balance.getMember());
        boolean jinddoStart=jinddoPayService.jinddoPayCreateS(balance);
        if(jinddoStart != false){
            return ResponseEntity.ok("진또페이 성공");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("진또 사용 실패");
        }
    }

    @PutMapping("/charge/{email}")
    public ResponseEntity<String> jinddoPayCharge(@RequestBody Balance balance, @PathVariable String email) {
        jinddoPayService.jinddoPayChargeS(balance, email);
        return ResponseEntity.ok("진또페이 성공");
    }
}
