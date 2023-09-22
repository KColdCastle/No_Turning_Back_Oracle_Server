package NoTurningBack.jinddobey.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data//게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity//DB 테이블과 연동되는 엔터티라고 설정
public class DealCheck {

    @Id
    @Column(name="post_id", unique = true)
    private String postId;

    @Column(name="seller_email")
    private String sellerEmail;

    @Column(name="buyer_email")
    private String buyerEmail;

    @Column(name="seller_check", nullable = false)
    @ColumnDefault("0")
    private boolean sellerCheck;

    @Column(name="buyer_check", nullable = false)
    @ColumnDefault("0")
    private boolean buyerCheck;

    @Column(nullable = false)
    @ColumnDefault("0")
    private boolean complete;

    @OneToMany(mappedBy = "dealCheck")
    private List<Deal> deal = new ArrayList<>();

}
