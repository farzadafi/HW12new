package entity;

public class User {
    private int id;
    private String fullName;
    private String nationalId;
    private String password;
    private TypeUser typeUser;

    public User(String fullName, String nationalId, String password, TypeUser typeUser) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.password = password;
        this.typeUser = typeUser;
    }

    public User(int id, String fullName, String nationalId, String password, TypeUser typeUser) {
        this.id = id;
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.password = password;
        this.typeUser = typeUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

    public User() {
    }
}
