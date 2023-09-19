package NoTurningBack.jinddobey.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name="TRUST_SEQ_GENERATOR", sequenceName = "TRUST_SEQ",initialValue = 1,allocationSize = 1)
public class Trust {

    @Id
    @Column(name = "trust_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRUST_SEQ_GENERATOR")
    private long trustId;

    private String email;

    @Column(name = "trust_num")
    @ColumnDefault("0")
    private int trustNum;

    @ColumnDefault("'F'")
    private char grade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email", referencedColumnName = "email")
    private Member member;
}
