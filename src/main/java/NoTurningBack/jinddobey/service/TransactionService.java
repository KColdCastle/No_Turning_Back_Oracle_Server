package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> checkAll();

    List<Transaction> buyerTransaction(String email);
    List<Transaction> sellerTransaction(String email);


    Transaction check(String postId);
    void maxPriceUpdateS(Transaction transaction);
    Transaction transactionAddS(Transaction transaction);

    boolean buyerCheckS(String postId);

    boolean sellerCheckS(String postId);

    boolean dealExcute(Transaction transaction);
}
