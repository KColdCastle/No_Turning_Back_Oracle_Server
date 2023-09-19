package NoTurningBack.jinddobey.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
@SequenceGenerator(name="BIDDINGLOG_SEQ_GENERATOR", sequenceName = "BIDDINGLOG_SEQ",initialValue = 1,allocationSize = 1)

public class BiddingLog {

    @Id
    @Column(name="log_num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIDDINGLOG_SEQ_GENERATOR")
    private int logNum;

    @Column(name="post_num")
    private int postNum;

    private int price;
    @Column(name="bidding_email")
    private String biddingEmail;

    @Column(name="bidding_timestamp")
    private Date biddingTimeStamp;
}
