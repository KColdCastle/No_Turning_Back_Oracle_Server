package NoTurningBack.jinddobey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity//DB 테이블과 연동되는 엔터티라고 설정
public class Balance {

    @Id
    @Column(name ="email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//ManyToOne이 항상 관계의 주인이다.
    @JoinColumn(name="email", referencedColumnName = "email")
    private Member member;

    @Builder.Default
    private Long balance = 0L;

//    @OneToMany(mappedBy = "balance", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Deposit> deposits = new ArrayList<>();
//
//
//    @OneToMany(mappedBy = "balance", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Withdraw> withdraws = new ArrayList<>();

}
