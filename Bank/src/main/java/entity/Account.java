package entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



import entity.enumoration.TypeAccount;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 4,unique = true)
    private String codeBranch;

    @Column(nullable = false,length = 10,unique = true)
    private String nationalId;

    @Column(nullable = false,unique = true)
    private String accountNumber;

    private Double budget;
    private String typeAccount;


    @Override
    public String toString() {
        return "Account{" +
                "codeBranch='" + codeBranch + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", budget=" + budget +
                ", typeAccount=" + typeAccount +
                '}';
    }
}
