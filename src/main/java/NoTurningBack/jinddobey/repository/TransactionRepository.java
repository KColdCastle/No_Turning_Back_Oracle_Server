package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByPostId(String postId);
    List<Transaction> findAll();

    List<Transaction> findAllByMaxEmail(String email);

    List<Transaction> findAllBySellerEmail(String email);

    Optional<Transaction> findByTransactionIdAndPostId(long transactionId, String postId);

    Optional<Transaction> findByTransactionId(long transactionId);
}
