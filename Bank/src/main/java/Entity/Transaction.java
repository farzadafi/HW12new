package Entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private String accountNumber,originCardNumber,destinationCardNumber,amount;
    private Date date;
    private Time time;
    private TypeTransaction typeTransaction;

    public Transaction() {
    }

    public Transaction(String accountNumber, String originCardNumber, String destinationCardNumber, String amount, Date date, Time time, TypeTransaction typeTransaction) {
        this.accountNumber = accountNumber;
        this.originCardNumber = originCardNumber;
        this.destinationCardNumber = destinationCardNumber;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.typeTransaction = typeTransaction;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOriginCardNumber() {
        return originCardNumber;
    }

    public String getDestinationCardNumber() {
        return destinationCardNumber;
    }

    public String getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOriginCardNumber(String originCardNumber) {
        this.originCardNumber = originCardNumber;
    }

    public void setDestinationCardNumber(String destinationCardNumber) {
        this.destinationCardNumber = destinationCardNumber;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", originCardNumber='" + originCardNumber + '\'' +
                ", destinationCardNumber='" + destinationCardNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", typeTransaction=" + typeTransaction +
                '}';
    }
}
