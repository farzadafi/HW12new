package Repository;

import Entity.CreditCard;
import Entity.TypeAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardRepository implements Repository<CreditCard> {
    private Connection connection = Singleton.getInstance().getConnection();

    public CreditCardRepository() throws SQLException, ClassNotFoundException {
        String createTable ="CREATE TABLE IF NOT EXISTS " +
                "CreditCard(id serial," +
                "accountnumber varchar(10) REFERENCES Account(accountnumber)," +
                "cardNumber varchar(30) PRIMARY KEY," +
                "cvv2 varchar(10)," +
                "expireDate varchar(20)," +
                "password varchar(50) ," +
                "status varchar(20))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    public boolean checkCardNumber(String cardNumber) throws SQLException {
        String check = "SELECT * FROM CreditCard WHERE cardNumber = ? AND status = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(check);
        preparedStatement.setString(1,cardNumber);
        preparedStatement.setString(2,"ACTIVE");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return true;
        else
            return false;
    }

    @Override
    public void add(CreditCard creditCard) throws SQLException {
        String insertCard = " INSERT INTO CreditCard(accountnumber,cardNumber,cvv2,expireDate,status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertCard);
        preparedStatement.setString(1,creditCard.getAccountNumber());
        preparedStatement.setString(2,creditCard.getCardNumber());
        preparedStatement.setString(3,creditCard.getCvv2());
        preparedStatement.setString(4,creditCard.getDate());
        preparedStatement.setString(5, String.valueOf(creditCard.getTypeAccount()));
        preparedStatement.executeUpdate();
    }

    @Override
    public int find(String input) throws SQLException {
        String find = "SELECT * FROM CreditCard WHERE cardNumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        preparedStatement.setString(1,input);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }

    public String[] show(String input) throws SQLException {
        String show = "select  * from creditcard INNER JOIN account ON account.accountnumber = creditcard.accountnumber where account.nationalid = ? AND creditcard.status = 'ACTIVE' ";
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        preparedStatement.setString(1,input);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.isBeforeFirst()){
            return null;
        }
        else {
            System.out.println("You have this card:");
            String numberId[] = new String[100];
            int empty = 0;
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ":" + resultSet.getString("cardNumber"));
                numberId[empty] = String.valueOf(resultSet.getInt("id"));
                empty++;
            }
            return numberId;
        }
    }

    public int findActiveCard(String accountNumber) throws SQLException {
        String findCard = "SELECT * FROM CreditCard WHERE accountnumber = ? AND status = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findCard);
        preparedStatement.setString(1,accountNumber);
        preparedStatement.setString(2,"ACTIVE");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }

    public void setPassword(int id,String password) throws SQLException {
        String set = "UPDATE creditCard SET password = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(set);
        preparedStatement.setString(1,password);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }

    public String[] returnInformationCard(int id) throws SQLException {
        String findNumberCard = "SELECT * FROM CreditCard WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findNumberCard);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String[] result2 = new String[5];
        result2[0] = resultSet.getString("accountnumber");
        result2[1] = resultSet.getString("cardNumber");
        result2[2] = resultSet.getString("password");
        result2[3] = resultSet.getString("expireDate");
        result2[4] = resultSet.getString("cvv2");
        return result2;
    }

    public void setInactiveCard(int id) throws SQLException {
        String setinactive = "UPDATE CreditCard SET status = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(setinactive);
        preparedStatement.setString(1,"INACTIVE");
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }

    public String returnAccountNumber(String cardNumber) throws SQLException {
        String account = "SELECT * FROM CreditCard WHERE cardNumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(account);
        preparedStatement.setString(1,cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("accountnumber");
    }

    public List<CreditCard> findAllCard(String accountNumber) throws SQLException {
        String show = "SELECT * FROM CreditCard WHERE accountnumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        preparedStatement.setString(1,accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<CreditCard> creditCardList = new ArrayList<>();
        while(resultSet.next()){
            CreditCard creditCard = new CreditCard();
            creditCard.setAccountNumber(resultSet.getString("accountnumber"));
            creditCard.setCardNumber(resultSet.getString("cardNumber"));
            creditCard.setCvv2(resultSet.getString("cvv2"));
            creditCard.setDate(resultSet.getString("expireDate"));
            creditCard.setTypeAccount(TypeAccount.valueOf(resultSet.getString("status")));
            creditCard.setPassword(resultSet.getString("password"));
            creditCardList.add(creditCard);
        }
        return creditCardList;
    }

}
