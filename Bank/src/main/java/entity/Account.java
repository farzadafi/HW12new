package entity;
import lombok.*;

import javax.persistence.*;



import entity.enumoration.TypeAccount;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 4)
    private String codeBranch;

    @Column(nullable = false,length = 10)
    private String nationalId;

    @Column(nullable = false,unique = true)
    private String accountNumber;

    private Double budget;

    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;

}
