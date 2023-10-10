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
        return transactionRepository.findAll();
    }

    @Override
    public Transaction check(String postId) {
        Transaction findPrice = transactionRepository.findByPostId(postId);
        return findPrice;
    }

    @Override
    public void maxPriceUpdateS(Transaction transaction) {
        Optional<Transaction> newBidder= transactionRepository.findByTransactionId(transaction.getTransactionId());
        //Optional<>은 데이터 값에서 null일 경우 오류나는 것을 방지해줌.
        Transaction Transaction1=newBidder.get();
        Transaction1.setMaxEmail(transaction.getMaxEmail());
        Transaction1.setCurrentPrice(transaction.getCurrentPrice());
        Transaction1.setMaxPrice(transaction.getMaxPrice());
        transactionRepository.save(Transaction1);
    }

    @Override
    public void transactionAddS(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
