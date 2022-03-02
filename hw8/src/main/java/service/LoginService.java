package service;

import entity.User;
import repository.LoginRepository;

import java.sql.SQLException;

public class LoginService {
    LoginRepository loginRepository = new LoginRepository();

    public User returnUser(String nationalId){
        try {
            return loginRepository.returnUser(nationalId);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
