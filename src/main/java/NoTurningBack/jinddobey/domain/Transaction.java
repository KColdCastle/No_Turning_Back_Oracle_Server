package NoTurningBack.jinddobey.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private long transactionId;

    @Column
    private long maxPrice;

    @Column
    private String maxEmail;

    @Column
    private long currentPrice;

    @Column(unique = true)
    private String postId;

    @Column
    private String sellerEmail;

    @Column
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss", timezone = "Asia/Seoul")
    private Date biddingTimeStamp;

    @Column(nullable = false)
    private boolean sellerCheck = true;

    @Column(nullable = false)
    private boolean buyerCheck = true;
    @JsonIgnore
    @OneToOne(mappedBy = "transaction")
    private Deal deal;
}
