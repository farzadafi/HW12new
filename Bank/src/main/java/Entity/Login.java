package Entity;

public class Login {
    private String username;
    private String password;
    private TypeUser typeUser;

    public Login(){}

    public Login(String username, String password, TypeUser typeUser) {
        this.username = username;
        this.password = password;
        this.typeUser = typeUser;
    }

    public Login(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }
}
