package NoTurningBack.jinddobey.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Data // 게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // DB 테이블과 연동되는 엔터티라고 설정
@Table(name = "TRANSACTION")
@SequenceGenerator(name = "TRANSACTION_SEQ_GENERATOR", sequenceName = "TRANSACTION_SEQ", initialValue = 1, allocationSize = 1)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_SEQ_GENERATOR")
    private long transactionId;

    @Column
    private long maxPrice;

    @Builder.Default
    private String maxEmail = "start";

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
    @Builder.Default
    private boolean sellerCheck = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean buyerCheck = false;

    @JsonIgnore
    @OneToOne(mappedBy = "transaction")
    private Deal deal;
}
