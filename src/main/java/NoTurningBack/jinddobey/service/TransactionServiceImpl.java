package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.*;
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
    public Transaction transactionAddS(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    @Override
    public boolean sellerCheckS(String postId) {
        Transaction transaction1=transactionRepository.findByPostId(postId);
        transaction1.setSellerCheck(transaction1.isSellerCheck());
        transactionRepository.save(transaction1);
        return dealExcute(transaction1);
    }
    @Override
    public boolean buyerCheckS(String postId) {
        Transaction transaction1=transactionRepository.findByPostId(postId);
        transaction1.setBuyerCheck(transaction1.isBuyerCheck());
        transactionRepository.save(transaction1);
        return true;
    }

    @Transactional
    @Override
    public boolean dealExcute(Transaction transaction) {
        if(transaction.isBuyerCheck()&&transaction.isSellerCheck()){
            System.out.println("이제실행");
            System.out.println("이체할 가격: "+transaction.getCurrentPrice());
            System.out.println("판매자 email: "+transaction.getSellerEmail());
            System.out.println("구매하는 사람 email: "+transaction.getMaxEmail());

            //출금내역생성
            Balance buyerBalance=balanceRepository.findByEmail(transaction.getMaxEmail());
            if(buyerBalance.getBalance()- transaction.getCurrentPrice()>0) {
                buyerBalance.setBalance(buyerBalance.getBalance() - transaction.getCurrentPrice());
                balanceRepository.save(buyerBalance);
                Withdraw withdraw = new Withdraw();
                withdraw.setBalance(buyerBalance);
                withdrawRepository.save(withdraw);

                //입금내역생성
                Balance sellerBalance=balanceRepository.findByEmail(transaction.getSellerEmail());
                sellerBalance.setBalance(sellerBalance.getBalance()+transaction.getCurrentPrice());
                Deposit deposit=new Deposit();
                deposit.setBalance(sellerBalance);
                depositRepository.save(deposit);

                //Deal 테이블 생성
                Deal deal = new Deal();
                deal.setTransaction(transaction);
                deal.setDeposit(deposit);
                deal.setWithdraw(withdraw);
                dealRepository.save(deal);

                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
