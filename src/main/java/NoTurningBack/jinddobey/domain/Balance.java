package NoTurningBack.jinddobey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String email;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)//ManyToOne이 항상 관계의 주인이다.
    @JoinColumn(name="email", referencedColumnName = "email")
    private Member member;

    @ColumnDefault("0")
    private Long balance;

    @JsonIgnore
    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private List<Deposit> deposits = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private List<ServicePrice> servicePrices = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private List<Withdraw> withdraws = new ArrayList<>();

}
