package entity;

public class Customer extends User {
    private String address;
    private Double balance;


    public Customer(String fullName, String nationalId, String password, TypeUser typeUser,String address,Double balance) {
        super(fullName, nationalId, password, typeUser);
        this.address = address;
        this.balance = balance;
    }

    public Customer(int id, String fullName, String nationalId, String password, TypeUser typeUser,String address,Double balance) {
        super(id, fullName, nationalId, password, typeUser);
        this.address = address;
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer() {
    }
}
