package Entity;

import java.sql.Date;

public class CreditCard {
    private String accountNumber,cardNumber,cvv2,password,date;
    private TypeAccount typeAccount;

    public CreditCard(String accountNumber, String cardNumber, String cvv2, String date, TypeAccount typeAccount) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.date = date;
        this.typeAccount = typeAccount;
    }

    public CreditCard() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv2() {
        return cvv2;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "accountNumber='" + accountNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", password='" + password + '\'' +
                ", date='" + date + '\'' +
                ", typeAccount=" + typeAccount +
                '}';
    }
}
