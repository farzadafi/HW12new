package Service;

import Entity.Login;
import Entity.TypeUser;
import Repository.LoginRepository;

import java.sql.SQLException;

public class LoginService {
    private LoginRepository loginRepository = new LoginRepository();

    public LoginService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public int findNationalId(String nationalId) throws SQLException {
        return loginRepository.find(nationalId);
    }

    //::::>
    public void addNewLogin(String username, String password, TypeUser typeUser) throws SQLException {
        Login login = new Login(username,password,typeUser);
        loginRepository.add(login);
    }

    public String findLogin(String username,String password) throws SQLException {
        return loginRepository.findLogin(username,password);
    }

}//
