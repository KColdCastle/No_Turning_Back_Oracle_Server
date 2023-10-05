package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> checkAll();
    Transaction check(String postId);
    void maxPriceUpdateS(Transaction transaction);
}
