package Entity;

public class Person {
    private String fullName,nationalId,codeBranch,password;
    TypeUser typeUser;

    public Person(String fullName, String nationalId, String codeBranch, String password, TypeUser typeUser) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.codeBranch = codeBranch;
        this.password = password;
        this.typeUser = typeUser;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public String getPassword() {
        return password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }
}
