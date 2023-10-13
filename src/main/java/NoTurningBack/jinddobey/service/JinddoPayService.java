package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Balance;

public interface JinddoPayService {

    void jinddoPayCreateS(Balance balance);

    void jinddoPayChargeS(Balance balance, String email);
}
