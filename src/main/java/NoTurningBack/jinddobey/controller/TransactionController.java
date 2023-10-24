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

    @GetMapping("buyerTransaction/{email}")
    public List<Transaction> buyerTransaction(@PathVariable String email){
        return transactionService.buyerTransaction(email);
    }
    @GetMapping("sellerTransaction/{email}")
    public List<Transaction> sellerTransaction(@PathVariable String email){
        return transactionService.sellerTransaction(email);
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
        Transaction trans =  transactionService.check(postId);
        System.out.println(trans);
        return trans;
    }

    @PostMapping("post")
    public Transaction transactionAdd(@RequestBody Transaction transaction){
        System.err.println(transaction);
        return transactionService.transactionAddS(transaction);
    }

    @PutMapping("sellerCheck/{postId}")
    public void sellerCheck(@PathVariable String postId){
        transactionService.sellerCheckS(postId);

    }
    @PutMapping("dealerCheck/{postId}")
    public void buyerCheck(@PathVariable String postId){
        transactionService.buyerCheckS(postId);
    }
}
