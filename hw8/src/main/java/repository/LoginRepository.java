package repository;

import entity.TypeUser;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    private Connection connection;

    public LoginRepository() {
        try {
            connection = Singleton.getInstance().getConnection();
        }catch (SQLException | ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }

    public User returnUser(String nationalId) throws SQLException {
            String findUser = "SELECT * FROM UserTable WHERE nationalId = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(findUser);
            preparedStatement.setString(1, nationalId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),resultSet.getString("fullName"), resultSet.getString("nationalId"), resultSet.getString("password"), TypeUser.valueOf(resultSet.getString("kind")));
                return user;
            } else
                return null;
    }
}
