package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Transaction;
import NoTurningBack.jinddobey.repository.BalanceRepository;
import NoTurningBack.jinddobey.repository.DealRepository;
import NoTurningBack.jinddobey.repository.DepositRepository;
import NoTurningBack.jinddobey.repository.WithdrawRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DealServiceImpl implements DealService {
    @Autowired
    WithdrawRepository withdrawRepository;
    @Autowired
    DepositRepository depositRepository;
    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    DealRepository dealRepository;

    @Override
    @Transactional
    public void transfer(Transaction transaction) {

    }
}
