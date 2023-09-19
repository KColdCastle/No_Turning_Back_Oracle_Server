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
public class JinddoPay {

    @Id
    private String email;

    private long balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email", referencedColumnName = "email")
    private Member member;

    @OneToMany(mappedBy = "jinddoPay")
    private List<Deal> deal = new ArrayList<>();

}
