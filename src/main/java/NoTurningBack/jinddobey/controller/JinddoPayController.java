package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.service.JinddoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("jinddoPay")
@CrossOrigin(origins = "*", maxAge = 3600) // #매우 중요!
@RestController
public class JinddoPayController {

    @Autowired
    JinddoPayService jinddoPayService;

    @PostMapping("/{create}") // 사용자 정보 변경 요청 컨트롤러
    public String jinddoPayCreate(@RequestBody Balance balance) {
        jinddoPayService.jinddoPayCreateS(balance);
        return "redirect:../";
    }

    @PutMapping("/charge/{email}") // 사용자 정보 변경 요청 컨트롤러
    public String jinddoPayCharge(@RequestBody Balance balance, @PathVariable String email) {
        jinddoPayService.jinddoPayChargeS(balance, email);
        return "redirect:../";
    }
}
