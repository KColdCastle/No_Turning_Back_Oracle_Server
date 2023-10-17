package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> checkAll();
    Transaction check(String postId);
    void maxPriceUpdateS(Transaction transaction);
    void transactionAddS(Transaction transaction);

    void buyerCheckS(Transaction transaction);

    void sellerCheckS(Transaction transaction);

    void dealExcute(Transaction transaction);
}
