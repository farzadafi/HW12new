package entity;

public class Admin extends User{

    public Admin(String fullName, String nationalId, String password, TypeUser typeUser) {
        super(fullName, nationalId, password, typeUser);
    }

    public Admin(int id, String fullName, String nationalId, String password, TypeUser typeUser) {
        super(id, fullName, nationalId, password, typeUser);
    }

    public Admin(){
        super();
    }
}
