package NoTurningBack.jinddobey.controller;

import NoTurningBack.jinddobey.domain.Transaction;
import NoTurningBack.jinddobey.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("transaction")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class TransactionController {
    @Autowired
    TransactionService service;

    @GetMapping("transaction_all")
    public List<Transaction> transactionAll(){
        System.out.println(service.checkAll());
        return service.checkAll();
    }

    @GetMapping("max_price")
    public long maxPriceCheck(String postId){
        long maxPrice=service.check(postId).getMaxPrice();
        return maxPrice;
    }
    @PutMapping("max_price")
    public void maxPriceUpdate(@RequestBody Transaction transaction){
        service.maxPriceUpdateS(transaction);
    }
    @GetMapping("max_email")
    public String maxEmailCheck(String postId){
        String maxEmail=service.check(postId).getMaxEmail();
        return maxEmail;
    }
    @GetMapping("current_price")
    public long currentPriceCheck(String postId){
        long currentPrice=service.check(postId).getCurrentPrice();
        return currentPrice;
    }

    @GetMapping("post/{postid}")
    @JsonIgnore
    public Transaction transactionSingle(@PathVariable String postId){
        return  service.check(postId);
    }
}
