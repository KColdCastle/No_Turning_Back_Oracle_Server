package NoTurningBack.jinddobey.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@SequenceGenerator(name="TRANSACTION_SEQ_GENERATOR", sequenceName = "TRANSACTION_SEQ",initialValue = 1,allocationSize = 1)

@Table(name="TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_SEQ_GENERATOR")
    @Column(name="transaction_num")
    private long transactionNum;

    @Column(name="post_id", unique = true)
    private String postId;

    @Column(name="seller_email")
    private String sellerEmail;

    @Column(name="current_price")
    private long currentPrice;

    @Column(name="bidding_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss", timezone = "Asia/Seoul")
    private Date biddingTimeStamp;

    @OneToOne(mappedBy = "transaction")
    private TransactionMax transactionMax;
}
