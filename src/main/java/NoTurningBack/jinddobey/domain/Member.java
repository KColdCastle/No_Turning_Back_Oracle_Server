package NoTurningBack.jinddobey.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data // 게터 세터
@AllArgsConstructor
@NoArgsConstructor
@Entity // DB 테이블과 연동되는 엔터티라고 설정
public class Member {

    @Id
    private String email;

    @Column(nullable = false)
    private String address;

    @Column
    private String phoneNum;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean state = true;

    @ColumnDefault("0")
    private int warning;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Date createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private Date passwordChangeDate;

    @JsonIgnore
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Balance balance;
}
