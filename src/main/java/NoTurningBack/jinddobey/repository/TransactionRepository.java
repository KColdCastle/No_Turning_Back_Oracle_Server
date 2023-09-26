package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByPostId(String postId);
    List<Transaction> findAll();
    Optional<Transaction> findByTransactionNumAndPostId(long transactionNum, String postId);
}
