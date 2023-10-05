package NoTurningBack.jinddobey.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
@Table(name="TRANSACTION")
@SequenceGenerator(name="TRANSACTION_SEQ_GENERATOR", sequenceName = "TRANSACTION_SEQ",initialValue = 1,allocationSize = 1)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_SEQ_GENERATOR")
    @Column(name="transaction_id")
    private long transactionId;

    @Column(name="max_price")
    private long maxPrice;

    @Column(name="max_email")
    private String maxEmail;

    @Column(name="current_price")
    private long currentPrice;

    @Column(name="post_id", unique = true)
    private String postId;

    @Column(name="seller_email")
    private String sellerEmail;

    @Column(name="bidding_timestamp")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss", timezone = "Asia/Seoul")
    private Date biddingTimeStamp;

    @OneToOne
    @JoinColumn(name="deal_id")
    private Deal deal;

}
