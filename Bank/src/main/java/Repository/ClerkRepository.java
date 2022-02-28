package Repository;

import Entity.Clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClerkRepository implements Repository<Clerk> {
    private Connection connection = Singleton.getInstance().getConnection();

    public ClerkRepository() throws SQLException, ClassNotFoundException {
        String createTable ="CREATE TABLE IF NOT EXISTS " +
                            "Clerk(id serial," +
                            "fullName varchar(50)," +
                            "nationalId varchar(50) PRIMARY KEY ," +
                            "codeBranch varchar(50)," + "" +
                            "password varchar(50))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    @Override
    public void add(Clerk clerk) throws SQLException {
        String insert = " INSERT INTO Clerk(fullName,nationalId,codeBranch,password) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1,clerk.getFullName());
        preparedStatement.setString(2,clerk.getNationalId());
        preparedStatement.setString(3,clerk.getCodeBranch());
        preparedStatement.setString(4,clerk.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public int find(String input) throws SQLException {
        return 0;
    }


    public String findCodeBranch(String nationalId) throws SQLException {
        String findCode = "SELECT * FROM Clerk WHERE nationalId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findCode);
        preparedStatement.setString(1,nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return resultSet.getString("codeBranch");
        else
            return null;
    }
}
