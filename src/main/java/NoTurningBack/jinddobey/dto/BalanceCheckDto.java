package NoTurningBack.jinddobey.dto;

import NoTurningBack.jinddobey.domain.Balance;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BalanceCheckDto {

    private String email;
    private Long balance;

    public static BalanceCheckDto of(Balance balance) {
        return new BalanceCheckDto(balance.getMember().getEmail(), balance.getBalance());
    }
}
