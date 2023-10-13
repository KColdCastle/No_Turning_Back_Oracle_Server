package NoTurningBack.jinddobey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data // 게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity // DB 테이블과 연동되는 엔터티라고 설정
@SequenceGenerator(name = "Deal_SEQ_GENERATOR", sequenceName = "Deal_SEQ", initialValue = 1, allocationSize = 1)
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Deal_SEQ_GENERATOR")
    private Long dealId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transactionId")
    // mappedBy 속성은 양방향 매핑일 때 사용하는데, 반대쪽 매핑의 필드 이름(deal)을 값으로 주면 됩니다.
    private Transaction transaction;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "deposit_id", referencedColumnName = "depositId")
    private Deposit deposit;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "withdraw_id", referencedColumnName = "withdrawId")
    private Withdraw withdraw;

}
