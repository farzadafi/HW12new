package repository;

import entity.Customer;
import entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository<Customer> {
    private Connection connection;
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public int add(Customer customer){
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(customer);
                transaction.commit();
                return customer.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }


    public CustomerRepository() {
        try {
            connection = Singleton.getInstance().getConnection();
        }catch (SQLException | ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }


    /*
    @Override
    public List<Customer> findAll() throws SQLException {
            String findAll = "SELECT * FROM UserTable" ;
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            List<Customer> customerList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setFullName(resultSet.getString("fullName"));
                    customer.setNationalId(resultSet.getString("nationalId"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setBalance(resultSet.getDouble("balance"));
                    customer.setAddress(resultSet.getString("address"));
                    customerList.add(customer);
                }
                return customerList;
            } else
                return null;
    }
     */

    @Override
    public List<Customer> findAll(){
            try (var session = sessionFactory.openSession()) {
                NativeQuery query = session.createSQLQuery("SELECT * FROM Usertable");
                query.addEntity(Customer.class);
                List account = null;
                try {
                    account = query.list();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return account;
            }
        }

    @Override
    public int update(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(customer);
                transaction.commit();
                return customer.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Customer findById(int id){
        try (var session = sessionFactory.openSession()) {
            Customer customer = session.find(Customer.class,id);
            return customer;
        }
    }



    /*
    @Override
    public int update(Customer customer) throws SQLException {
            String update = "UPDATE UserTable SET fullName = ? , password = ? , address = ?  WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,customer.getFullName());
            preparedStatement.setString(2,customer.getPassword());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setInt(4,customer.getId());
            return preparedStatement.executeUpdate();
    }

     */

    @Override
    public int delete(int id) throws SQLException {
            String del = "DELETE FROM UserTable WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(del);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
    }

    public int addBudget(int id,Double amount) throws SQLException {
            String deposit = "UPDATE UserTable SET balance = usertable.balance + ? where id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(deposit);
            preparedStatement.setDouble(1,amount);
            preparedStatement.setInt(2,id);
            return preparedStatement.executeUpdate();
    }

    public Double returnBudget(int id) throws SQLException {
            String budget = "SELECT balance FROM UserTable WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(budget);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getDouble("balance");
            else
                return 0d;
    }



}
