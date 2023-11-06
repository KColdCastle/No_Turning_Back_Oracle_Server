package NoTurningBack.jinddobey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // 게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity // DB 테이블과 연동되는 엔터티라고 설정
@SequenceGenerator(name = "Deposit_SEQ_GENERATOR", sequenceName = "Deposit_SEQ", initialValue = 1, allocationSize = 1)

public class Deposit { // 입금
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Deposit_SEQ_GENERATOR")
    private Long depositId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "email")
    private Balance balance;


}
