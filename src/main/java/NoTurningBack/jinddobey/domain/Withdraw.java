package NoTurningBack.jinddobey.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
@SequenceGenerator(name="Withdraw_SEQ_GENERATOR", sequenceName = "Withdraw_SEQ",initialValue = 1,allocationSize = 1)
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Withdraw_SEQ_GENERATOR")
    private Long withdrawId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="email", referencedColumnName = "email")
    private Balance balance;

    @JsonIgnore
    @OneToMany(mappedBy = "withdraw")
    private List<Deal> deals = new ArrayList<>();
}
