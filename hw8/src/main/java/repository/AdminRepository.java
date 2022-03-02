package repository;

import entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements Repository<Admin> {
    private Connection connection;

    public AdminRepository(){
        try {
            this.connection = Singleton.getInstance().getConnection();
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int add(Admin admin) throws SQLException {
            String add = "INSERT INTO UserTable(fullName,nationalId,password,typeuser,address,balance) VALUES (?,?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, admin.getFullName());
            preparedStatement.setString(2, admin.getNationalId());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setString(4, String.valueOf(admin.getTypeUser()));
            preparedStatement.setString(5, null);
            preparedStatement.setDouble(6, 0);
            return preparedStatement.executeUpdate();
    }

    @Override
    public List<Admin> findAll() throws SQLException {
            String findAll = "SELECT * FROM UserTable" ;
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            List<Admin> adminList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setId(resultSet.getInt("id"));
                    admin.setFullName(resultSet.getString("fullName"));
                    admin.setNationalId(resultSet.getString("nationalId"));
                    admin.setPassword(resultSet.getString("password"));
                    adminList.add(admin);
                }
                return adminList;
            } else
                return null;
    }

    @Override
    public int update(Admin admin) throws SQLException {
            String update = "UPDATE UserTable SET fullName = ? , password = ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,admin.getFullName());
            preparedStatement.setString(2,admin.getPassword());
            preparedStatement.setInt(3,admin.getId());
            return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(int id) throws SQLException {
            String del = "DELETE FROM UserTable WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(del);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
    }
}
