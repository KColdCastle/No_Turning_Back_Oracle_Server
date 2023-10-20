package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.dto.BalanceCheckDto;
import NoTurningBack.jinddobey.repository.BalanceRepository;
import NoTurningBack.jinddobey.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JinddoPayServiceImpl implements JinddoPayService {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public BalanceCheckDto jinddoPayBalance(String email) {
        System.out.println("데이터베이스 검색: "+balanceRepository.findByMember_Email(email));
        Balance balance = balanceRepository.findByMember_Email(email);
        if(balance == null){
            return null;
        }else{
            return BalanceCheckDto.of(balance);
        }
    }

    @Override
    public boolean jinddoPayCreateS(Balance balance) {
        balanceRepository.save(balance);
        return true;
    }

    @Override
    public boolean jinddoPayChargeS(Balance balance, long amount) {
        System.out.println("입금하는 금액: "+amount);
        Balance chargingBalance = balanceRepository.findByMember_Email(balance.getEmail());
        chargingBalance.setBalance(balance.getBalance()+amount);
        balanceRepository.save(chargingBalance);
        return true;
    }
}