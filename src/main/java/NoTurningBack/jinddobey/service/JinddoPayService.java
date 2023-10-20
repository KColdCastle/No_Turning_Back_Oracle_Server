package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;
import NoTurningBack.jinddobey.dto.BalanceCheckDto;

public interface JinddoPayService {

    long jinddoPayBalance(String email);

    boolean jinddoPayCreateS(Balance balance);

    boolean jinddoPayChargeS(Balance balance, String email);
}
