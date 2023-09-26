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

//    private String email;

    private Long balance;


    @OneToMany(mappedBy = "jinddoPay", cascade = CascadeType.ALL)
    private List<Deal> deal = new ArrayList<>();

}
