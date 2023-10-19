package NoTurningBack.jinddobey.controller;


import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.service.BalanceService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("jinddo")
@CrossOrigin(origins = "*", maxAge = 3600) // #매우 중요!
@RestController
public class BalanceController {

    BalanceService balanceService;

    @PostMapping("account")
    public void jinddoPayStart(@RequestBody Balance balance){
        balanceService.jinddoPayStartS(balance);
    }

    @PutMapping("charge")
    public void charge(@RequestBody Balance balance){
        balanceService.balanceUpdateS(balance);
    }

}
