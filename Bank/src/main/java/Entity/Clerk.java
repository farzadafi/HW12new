package Entity;

public class Clerk extends Person{

    public Clerk(String fullName, String nationalId, String codeBranch, String password, TypeUser typeUser) {
        super(fullName, nationalId, codeBranch, password, typeUser);
    }

    public String getFullName(){
        return super.getFullName();
    }

    public String getNationalId(){
        return super.getNationalId();
    }

    public String getCodeBranch(){
        return super.getCodeBranch();
    }

    public String getPassword(){
        return super.getPassword();
    }



}
