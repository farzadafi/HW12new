package repository;

import entity.Account;
import entity.enumoration.TypeAccount;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account> {
    private Connection connection = Singleton.getInstance().getConnection();;
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public AccountRepository() throws SQLException, ClassNotFoundException {
    }


    public int add(Account account){
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
                return account.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public String findById(int id){
        try (var session = sessionFactory.openSession()) {
            Account account = session.find(Account.class,id);
            if(account == null )
                return null;
            else
                return account.getAccountNumber();
        }
    }

    public int find(String accountNumber) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createSQLQuery("SELECT * FROM Account WHERE accountnumber = :accountNumber");
            query.addEntity(Account.class);
            query.setParameter("accountNumber",accountNumber);
            List account = null;
            try {
                account = query.list();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if( account == null || account.size() == 0 ) {
                System.out.println("This account is not found!");
                return 0;
            }
            else {
                System.out.println("This account is found!");
                return 1;
            }
        }
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
            account.setTypeAccount(TypeAccount.valueOf(resultSet.getString("TypeAccount")));
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
        if(resultSet.getString("TypeAccount").equals("ACTIVE"))
            return true;
        else
            return false;
    }

    public void setInactiveAccount(String accountNumber) throws SQLException {
        String update = "UPDATE Account SET TypeAccount = ? WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1,"INACTIVE");
        preparedStatement.setString(2,accountNumber);
        preparedStatement.executeUpdate();
    }



}




