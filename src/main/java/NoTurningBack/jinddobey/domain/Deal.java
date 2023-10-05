package NoTurningBack.jinddobey.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
public class Deal {

    @Id
    @Column(name="deal_id", unique = true)
    private String dealId;

    @OneToOne(mappedBy = "deal")
    //mappedBy 속성은 양방향 매핑일 때 사용하는데, 반대쪽 매핑의 필드 이름(deal)을 값으로 주면 됩니다.
    private Transaction transactionId;

    @ManyToOne
    private Deposit deposit;

    @ManyToOne
    private ServicePrice servicePrice;

    @ManyToOne
    private Withdraw withdraw;


    @Column(name="seller_check", nullable = false)
    @ColumnDefault("0")
    private boolean sellerCheck;

    @Column(name="buyer_check", nullable = false)
    @ColumnDefault("0")
    private boolean buyerCheck;
}
