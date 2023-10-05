package NoTurningBack.jinddobey.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
public class Balance {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)//ManyToOne이 항상 관계의 주인이다.
    private Member member;

    @ColumnDefault("0")
    private Long balance;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private List<Deposit> deposits = new ArrayList<>();

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private List<ServicePrice> servicePrices = new ArrayList<>();

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private List<Withdraw> withdraws = new ArrayList<>();

}
