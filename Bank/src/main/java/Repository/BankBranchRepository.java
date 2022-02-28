package Repository;

import Entity.BankBranch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankBranchRepository implements Repository<BankBranch> {
    private Connection connection = Singleton.getInstance().getConnection();

    //::::>
    public BankBranchRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS BankBranch(id serial," +
                                                                    "nameBank varchar(50) REFERENCES Bank(nameBank)," +
                                                                    "codeBranch varchar(50) PRIMARY KEY,"+
                                                                    "BossFullName varchar(50)," +
                                                                    "nationalId varchar(50)," +
                                                                    "password varchar(50) )";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    @Override
    public void add(BankBranch bankBranch) throws SQLException {
        String insertBank = "INSERT INTO BankBranch (nameBank,codeBranch,BossfullName,nationalId,password) VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(insertBank);
        preparedStatement.setString(1,bankBranch.getNameBank());
        preparedStatement.setString(2,bankBranch.getCodeBranch());
        preparedStatement.setString(3,bankBranch.getBossFullName());
        preparedStatement.setString(4,bankBranch.getNationalId());
        preparedStatement.setString(5,bankBranch.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public int find(String input) throws SQLException {
        String find = "SELECT * FROM BankBranch WHERE codeBranch = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        preparedStatement.setString(1,input);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }


    public String findCodeBranch(String nationalId) throws SQLException {
        String findCode = "SELECT * FROM BankBranch WHERE nationalId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findCode);
        preparedStatement.setString(1,nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return resultSet.getString("codeBranch");
        else
            return null;
    }
}
