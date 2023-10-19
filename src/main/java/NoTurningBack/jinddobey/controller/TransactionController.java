package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Transaction;
import NoTurningBack.jinddobey.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("transaction")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("transaction_all")
    public List<Transaction> transactionAll(){
        return transactionService.checkAll();
    }

    @GetMapping("max_price/{postId}")
    public long maxPriceCheck(@PathVariable String postId){
        long maxPrice=transactionService.check(postId).getMaxPrice();
        return maxPrice;
    }
    @PutMapping("max_price")
    public void maxPriceUpdate(@RequestBody Transaction transaction){
        System.out.println(transaction);
        transactionService.maxPriceUpdateS(transaction);
    }
    @GetMapping("max_email/{postId}")
    public String maxEmailCheck(@PathVariable String postId){
        String maxEmail=transactionService.check(postId).getMaxEmail();
        return maxEmail;
    }
    @GetMapping("current_price/{postId}")
    public long currentPriceCheck(@PathVariable String postId){
        long currentPrice=transactionService.check(postId).getCurrentPrice();
        return currentPrice;
    }

    @GetMapping("post/{postId}")
    public Transaction transactionSingle(@PathVariable String postId){
        return transactionService.check(postId);
    }

    @PostMapping("post")
    public void transactionAdd(@RequestBody Transaction transaction){
        transactionService.transactionAddS(transaction);
    }

    @PutMapping("sellerCheck")
    public void sellerCheck(@RequestBody Transaction transaction){
        transactionService.sellerCheckS(transaction);
    }
    @PutMapping("dealerCheck")
    public void buyerCheck(@RequestBody Transaction transaction){
        transactionService.buyerCheckS(transaction);
    }

}
