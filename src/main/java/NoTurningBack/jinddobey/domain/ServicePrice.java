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
@SequenceGenerator(name="ServicePrice_SEQ_GENERATOR", sequenceName = "ServicePrice_SEQ",initialValue = 1,allocationSize = 1)
public class ServicePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ServicePrice_SEQ_GENERATOR")
    private Long servicePriceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="email", referencedColumnName = "email")
    private Balance balance;

    private Long amount;

    @JsonIgnore
    @OneToMany(mappedBy = "servicePrice")
    private List<Deal> deals = new ArrayList<>();
}
