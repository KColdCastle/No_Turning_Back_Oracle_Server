package NoTurningBack.jinddobey.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
@Table(name="TRANSACTION_MAX")
@SequenceGenerator(name="TRANSACTIONMAX_SEQ_GENERATOR", sequenceName = "TRANSACTIONMAX_SEQ",initialValue = 1,allocationSize = 1)
public class TransactionMax {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTIONMAX_SEQ_GENERATOR")
    @Column(name="transaction_num")
    private long transactionMaxNum;

    @Column(name="post_num")
    private int postNum;

    @Column(name="max_price")
    private int maxPrice;

    @Column(name="max_email")
    private String maxEmail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_post_num")
    private Transaction transaction;
}
