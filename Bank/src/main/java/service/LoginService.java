package service;

import entity.Login;
import entity.enumoration.TypeUser;
import repository.LoginRepository;

import java.sql.SQLException;

public class LoginService {
    private LoginRepository loginRepository = new LoginRepository();

    public LoginService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public int findNationalId(String nationalId) {
        try {
            return loginRepository.find(nationalId);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    //::::>
    public void addNewLogin(String username, String password, TypeUser typeUser) {
        Login login = new Login(username,password,typeUser);
        try {
            loginRepository.add(login);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String findLogin(String username,String password) {
        try {
            return loginRepository.findLogin(username,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}//
