package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.repository.BalanceRepository;
import NoTurningBack.jinddobey.repository.DepositRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class BalanceServiceImpl implements BalanceService {

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    BalanceRepository balanceRepository;


    @Override
    public void jinddoPayStartS(Balance balance) {
        balanceRepository.save(balance);
    }

    @Transactional
    @Override
    public void balanceUpdateS(Balance balance) {
//        depositRepository.jinddoSave(balance.getEmail());
        Balance balance1=balanceRepository.findByEmail(balance.getEmail());
        balance1.setBalance(balance.getBalance());
        balanceRepository.save(balance1);
    }
}
