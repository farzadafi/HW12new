package Entity;
import java.math.BigInteger;

public class Account {
    private String codeBranch,nationalId,accountNumber;
    Double budget;
    TypeAccount typeAccount;

    public Account(String codeBranch, String nationalId, String accountNumber, Double budget,TypeAccount typeAccount) {
        this.codeBranch = codeBranch;
        this.nationalId = nationalId;
        this.accountNumber = accountNumber;
        this.budget = budget;
        this.typeAccount = typeAccount;
    }

    public Account() {
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBudget() {
        return budget;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setCodeBranch(String codeBranch) {
        this.codeBranch = codeBranch;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

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
