package entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



import entity.enumoration.TypeAccount;

import java.util.Objects;

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

    @Column(nullable = false,length = 4)
    private String codeBranch;

    @Column(nullable = false,length = 10)
    private String nationalId;

    @Column(nullable = false,unique = true)
    private String accountNumber;

    private Double budget;

    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(codeBranch, account.codeBranch) && Objects.equals(nationalId, account.nationalId) && Objects.equals(accountNumber, account.accountNumber) && Objects.equals(budget, account.budget) && typeAccount == account.typeAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeBranch, nationalId, accountNumber, budget, typeAccount);
    }
}
