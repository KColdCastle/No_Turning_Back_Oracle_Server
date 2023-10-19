package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Transaction;
import NoTurningBack.jinddobey.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    WithdrawRepository withdrawRepository;
    @Autowired
    DepositRepository depositRepository;
    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    DealRepository dealRepository;
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
    @Override
    public void sellerCheckS(Transaction transaction) {
        Transaction transaction1=transactionRepository.findByPostId(transaction.getPostId());
        transaction1.setSellerCheck(transaction1.isSellerCheck());
        transactionRepository.save(transaction1);

    }
    @Override
    public void buyerCheckS(Transaction transaction) {
        Transaction transaction1=transactionRepository.findByPostId(transaction.getPostId());
        transaction1.setBuyerCheck(transaction1.isBuyerCheck());
        transactionRepository.save(transaction1);
        dealExcute(transaction1);
    }

    @Transactional
    @Override
    public void dealExcute(Transaction transaction) {
        if(transaction.isBuyerCheck()==transaction.isSellerCheck()){
            System.out.println("이제실행");
            System.out.println("이체할 가격: "+transaction.getCurrentPrice());
            System.out.println("받을사람 email: "+transaction.getSellerEmail());
            System.out.println("구매하는 사람 email: "+transaction.getMaxEmail());

        }
    }
}
