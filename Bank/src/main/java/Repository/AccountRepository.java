package Repository;

import Entity.Account;
import Entity.TypeAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account> {
    private Connection connection = Singleton.getInstance().getConnection();;

    //::::>
    public AccountRepository() throws SQLException, ClassNotFoundException {
        String createTable ="CREATE TABLE IF NOT EXISTS " +
                "Account(id serial," +
                "codeBranch varchar(50) REFERENCES BankBranch(codeBranch)," +
                "nationalId varchar(50) REFERENCES Customer(nationalId)," +
                "accountnumber varchar(50) PRIMARY KEY," +
                "budget DECIMAL, " +
                "status varchar(50))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    @Override
    public void add(Account account) throws SQLException {
        String insertAccount = " INSERT INTO Account(codeBranch,nationalId,accountnumber,budget,status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertAccount);
        preparedStatement.setString(1,account.getCodeBranch());
        preparedStatement.setString(2,account.getNationalId());
        preparedStatement.setString(3,account.getAccountNumber());
        preparedStatement.setDouble(4,account.getBudget());
        preparedStatement.setString(5, String.valueOf(account.getTypeAccount()));
        preparedStatement.executeUpdate();
    }

    @Override
    public int find(String accountNumber) throws SQLException {
        String find = "SELECT * FROM Account WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        preparedStatement.setString(1,accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }

    public int showAccount(String nationalId) throws SQLException {
        String show = "SELECT * FROM Account WHERE nationalId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        preparedStatement.setString(1,nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if ((resultSet.getString("status").equals("ACTIVE"))) {
                    System.out.print(resultSet.getInt("id") + ": ");
                    System.out.println(resultSet.getString("accountnumber"));
                }
            }
            return 1;
    }

    public String returnAccountNumber(int id) throws SQLException {
        String returnNumber = "SELECT * FROM Account WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(returnNumber);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return resultSet.getString("accountnumber");
        else
            return "null";
    }

    public String returnAmount(String accountNumber) throws SQLException {
        String returnAmount = "SELECT * FROM Account WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(returnAmount);
        preparedStatement.setString(1,accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("budget");
    }

    public void depositCard(Double amount,String accountNumber) throws SQLException {
        String deposit = "UPDATE Account SET budget = budget-? WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(deposit);
        preparedStatement.setDouble(1,amount);
        preparedStatement.setString(2,accountNumber);
        preparedStatement.executeUpdate();
    }

    public void withdrawCard(Double amount,String accountNumber) throws SQLException {
        String deposit = "UPDATE Account SET budget = budget+? WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(deposit);
        preparedStatement.setDouble(1,amount);
        preparedStatement.setString(2,accountNumber);
        preparedStatement.executeUpdate();
    }

    public List<Account> showAllAccount(String nationalId) throws SQLException {
        String show = "SELECT * FROM Account WHERE nationalId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        preparedStatement.setString(1,nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Account> accountList = new ArrayList<>();
        while(resultSet.next()){
            Account account = new Account();
            account.setCodeBranch(resultSet.getString("codeBranch"));
            account.setNationalId(resultSet.getString("nationalId"));
            account.setAccountNumber(resultSet.getString("accountnumber"));
            account.setBudget(resultSet.getDouble("budget"));
            account.setTypeAccount(TypeAccount.valueOf(resultSet.getString("status")));
            accountList.add(account);
        }
        return accountList;
    }

    public boolean checkAccount(String accountNumber) throws SQLException {
        String check = "SELECT * FROM Account WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(check);
        preparedStatement.setString(1,accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        if(resultSet.getString("status").equals("ACTIVE"))
            return true;
        else
            return false;
    }

    public void setInactiveAccount(String accountNumber) throws SQLException {
        String update = "UPDATE Account SET status = ? WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1,"INACTIVE");
        preparedStatement.setString(2,accountNumber);
        preparedStatement.executeUpdate();
    }



}




