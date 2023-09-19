package NoTurningBack.jinddobey.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
@SequenceGenerator(name="DEAL_SEQ_GENERATOR", sequenceName = "DEAL_SEQ",initialValue = 1,allocationSize = 1)

public class Deal {

    @Id
    @Column(name="deal_num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEAL_SEQ_GENERATOR")
    private int delNum;

    @Column(name="post_num")
    private int postNum;

    @Column(name="buyer_email")
    private String buyerEmail;

    @Column(name="seller_email")
    private String sellerEmail;

    @ManyToOne
    @JoinColumn(name="jinddopay_email", referencedColumnName = "email")
    private JinddoPay jinddoPay;

    @ManyToOne
    @JoinColumn(name="dealcheck_post_num",referencedColumnName = "post_num")
    private DealCheck dealCheck;
}
