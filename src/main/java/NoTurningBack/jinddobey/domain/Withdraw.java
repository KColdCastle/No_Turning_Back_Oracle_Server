package NoTurningBack.jinddobey.domain;


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
public class Withdraw {

    @Id
    @Column(name="withdraw_id")
    private Long withdrawId;

    @ManyToOne
    private Balance email;

    @OneToMany(mappedBy = "withdraw")
    private List<Deal> deals = new ArrayList<>();
}
