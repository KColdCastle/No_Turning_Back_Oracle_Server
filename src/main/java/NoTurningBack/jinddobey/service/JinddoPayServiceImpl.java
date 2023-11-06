package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.dto.BalanceCheckDto;
import NoTurningBack.jinddobey.repository.BalanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JinddoPayServiceImpl implements JinddoPayService {
    @Autowired
    BalanceRepository balanceRepository;

    @Override
    public BalanceCheckDto jinddoPayBalance(String email) {
        System.out.println("데이터베이스 검색: " + balanceRepository.findByMember_Email(email));
        Balance balance = balanceRepository.findByMember_Email(email);
        if (balance == null) {
            System.out.println("계좌없음");
            return null;
        } else {
            System.out.println("계좌있음");
            return BalanceCheckDto.of(balance);
        }
    }

    @Override
    public boolean jinddoPayCreateS(Balance balance) {
        balanceRepository.save(balance);
        return true;
    }

    @Transactional
    @Override
    public boolean jinddoPayChargeS(Balance balance, long amount) {
        System.out.println("입금하는 금액: " + amount);
        Balance chargingBalance = balanceRepository.findByMember_Email(balance.getEmail());
        System.out.println("기존 계좌 잔액: " + chargingBalance.getBalance());
        chargingBalance.setBalance(chargingBalance.getBalance() + amount);
        System.out.println("거래 후 계좌 잔액: " + chargingBalance.getBalance());
        balanceRepository.save(chargingBalance);
        return true;
    }
}
