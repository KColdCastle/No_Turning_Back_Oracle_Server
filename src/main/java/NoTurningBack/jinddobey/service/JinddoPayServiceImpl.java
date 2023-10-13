package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JinddoPayServiceImpl implements JinddoPayService {
    @Autowired
    BalanceRepository balanceRepository;


    @Override
    public void jinddoPayCreateS(Balance balance) {
        balanceRepository.save(balance);
    }

    @Override
    public void jinddoPayChargeS(Balance balance, String email) {
        Balance chargingBalance = balanceRepository.findByEmail(email);
        chargingBalance.setBalance(balance.getBalance());
        balanceRepository.save(chargingBalance);
    }
}
