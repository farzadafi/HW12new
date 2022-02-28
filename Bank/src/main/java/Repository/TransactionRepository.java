package Repository;

import Entity.Transaction;
import Entity.TypeTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements Repository<Transaction> {
    private Connection connection = Singleton.getInstance().getConnection();

    public TransactionRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS TransactionTable(id serial," +
                "accountnumber varchar(10)," +
                "originCardNumber varchar(20),"+
                "destinationCardNumber varchar(20)," +
                "amount varchar(50)," +
                "dateT date," +
                "timet time," +
                "typeTransaction varchar(20))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }


    @Override
    public void add(Transaction transaction) throws SQLException {
        String insertTransaction = "INSERT INTO TransactionTable (accountnumber,originCardNumber,destinationCardNumber,amount,dateT,timeT,typeTransaction) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(insertTransaction);
        preparedStatement.setString(1,transaction.getAccountNumber());
        preparedStatement.setString(2,transaction.getOriginCardNumber());
        preparedStatement.setString(3,transaction.getDestinationCardNumber());
        preparedStatement.setString(4,transaction.getAmount());
        preparedStatement.setDate(5,transaction.getDate());
        preparedStatement.setTime(6,transaction.getTime());
        preparedStatement.setString(7, String.valueOf(transaction.getTypeTransaction()));
        preparedStatement.executeUpdate();
    }

    public List<Transaction> findAllTransaction(String accountNumber, Date date) throws SQLException {
        String find = "SELECT * FROM TransactionTable WHERE accountnumber = ? AND dateT >= ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        preparedStatement.setString(1,accountNumber);
        preparedStatement.setDate(2,date);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Transaction> transactionList = new ArrayList<>();
        while(resultSet.next()){
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(resultSet.getString("accountnumber"));
            transaction.setOriginCardNumber(resultSet.getString("originCardNumber"));
            transaction.setDestinationCardNumber(resultSet.getString("destinationCardNumber"));
            transaction.setAmount(resultSet.getString("amount"));
            transaction.setDate(resultSet.getDate("dateT"));
            transaction.setTime(resultSet.getTime("timeT"));
            transaction.setTypeTransaction(TypeTransaction.valueOf(resultSet.getString("typeTransaction")));
            transactionList.add(transaction);
        }
        return transactionList;
    }


    @Override
    public int find(String input) throws SQLException {
        return 0;
    }
}
