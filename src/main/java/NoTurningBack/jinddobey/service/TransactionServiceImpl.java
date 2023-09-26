package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Transaction;
import NoTurningBack.jinddobey.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> checkAll() {
        System.out.println("transaction:"+transactionRepository.findAll());
        return transactionRepository.findAll();
    }

    @Override
    public Transaction check(String postId) {
        Transaction findPrice = transactionRepository.findByPostId(postId);
        return findPrice;
    }

    @Override
    public void maxPriceUpdateS(Transaction transaction) {
        Optional<Transaction> newBidder= transactionRepository.findByTransactionNumAndPostId(transaction.getTransactionNum(), transaction.getPostId());
        Transaction Transaction1=newBidder.get();
        Transaction1.setMaxEmail(transaction.getMaxEmail());
        Transaction1.setCurrentPrice(transaction.getCurrentPrice());
        transactionRepository.save(Transaction1);
    }
}
