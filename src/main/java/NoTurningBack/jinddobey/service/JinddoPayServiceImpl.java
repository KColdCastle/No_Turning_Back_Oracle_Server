package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.BalanceRepository;
import NoTurningBack.jinddobey.repository.MemberRepository;
import jakarta.persistence.EntityManager;
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
    public long jinddoPayBalance(String email) {

        return balanceRepository.findByEmail(email).getBalance();
    }

    @Override
    public boolean jinddoPayCreateS(Balance balance) {
        Balance jinddoAccount = balanceRepository.findByEmail(balance.getEmail());
        if(jinddoAccount == null) {
            Balance jinddoAccount1 = new Balance();
            Member jinddoUser = memberRepository.findByEmail(balance.getEmail());
            System.out.println("검색결과 계좌: "+ jinddoAccount);
            System.out.println("검색결과 유저: "+ jinddoUser);
            jinddoAccount1.setEmail(balance.getEmail());
            jinddoAccount1.setMember(jinddoUser);
            System.out.println("추가할 계좌정보: "+jinddoAccount1);
            balanceRepository.save(jinddoAccount1);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean jinddoPayChargeS(Balance balance, String email) {
        Balance chargingBalance = balanceRepository.findByEmail(email);
        chargingBalance.setBalance(balance.getBalance());
        balanceRepository.save(chargingBalance);
        return false;
    }
}
