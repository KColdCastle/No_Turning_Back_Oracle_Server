package NoTurningBack.jinddobey.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.sql.Date;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
@Table(name="TRANSACTION")
public class Transaction {
    @Id
    @Column(name="post_name")
    private int postNum;

    @Column(name="seller_email")
    private String sellerEmail;

    @Column(name="current_price")
    private int currentPrice;

    @Column(name="bidding_timestamp")
    private Date biddingTimeStamp;

    @OneToOne(mappedBy = "transaction")
    private TransactionMax transactionMax;
}
