package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;

public interface JinddoPayService {

    long jinddoPayBalance(String email);

    boolean jinddoPayCreateS(Balance balance);

    boolean jinddoPayChargeS(Balance balance, String email);
}
