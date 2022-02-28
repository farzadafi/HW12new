package Repository;

import Entity.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRepository implements Repository<Bank>{
    private Connection connection = Singleton.getInstance().getConnection();

    //::::>
    public BankRepository() throws SQLException, ClassNotFoundException {
        String createTableBank = "CREATE TABLE IF NOT EXISTS Bank(id serial,nameBank varchar(50) PRIMARY KEY)";
        PreparedStatement preparedStatement = connection.prepareStatement(createTableBank);
        preparedStatement.execute();
    }

    //::::>
    public void add(Bank bank) throws SQLException {
        String insertBank = "INSERT INTO Bank (nameBank) VALUES (?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(insertBank);
        preparedStatement.setString(1,bank.getName());
        preparedStatement.executeUpdate();
    }

    //::::>
    public int find(String name) throws SQLException {
        String find = "SELECT * FROM Bank WHERE nameBank = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }



}//
