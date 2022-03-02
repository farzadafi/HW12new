package repository;

import entity.CustomerBasket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBasketRepository implements Repository<CustomerBasket> {
    private Connection connection;

    public CustomerBasketRepository() {
        try{
            connection = Singleton.getInstance().getConnection();
            String CreateTable = "CREATE TABLE IF NOT EXISTS CustomerBasket(id serial PRIMARY KEY," +
                    "customerId integer REFERENCES UserTable(id)," +
                    "productId integer ," +
                    "numberProduct integer," +
                    "price DECIMAL)";
            PreparedStatement preparedStatement = connection.prepareStatement(CreateTable);
            preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public int add(CustomerBasket customerBasket) throws SQLException {
            String add = "INSERT INTO CustomerBasket(customerId,productId,numberProduct,price) VALUES (?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setInt(1, customerBasket.getCustomerId());
            preparedStatement.setInt(2, customerBasket.getProductId());
            preparedStatement.setInt(3, customerBasket.getNumber());
            preparedStatement.setDouble(4, customerBasket.getTotalPrice());
            return preparedStatement.executeUpdate();
    }

    @Override
    public List<CustomerBasket> findAll() throws SQLException {
            String findAll = "SELECT * FROM CustomerBasket ";
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            List<CustomerBasket> customerBasketList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    CustomerBasket customerBasket = new CustomerBasket();
                    customerBasket.setId(resultSet.getInt("id"));
                    customerBasket.setCustomerId(resultSet.getInt("customerId"));
                    customerBasket.setProductId(resultSet.getInt("productId"));
                    customerBasket.setNumber(resultSet.getInt("numberProduct"));
                    customerBasket.setTotalPrice(resultSet.getDouble("price"));
                    customerBasketList.add(customerBasket);
                }
                return customerBasketList;
            } else
                return null;
    }

    @Override
    public int update(CustomerBasket customerBasket) {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
            String del = "DELETE FROM CustomerBasket WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(del);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
    }

    public List<CustomerBasket> customerBasketById(int id) throws SQLException {
            String findAll = "SELECT * FROM CustomerBasket WHERE customerId = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            preparedStatement.setInt(1,id);
            List<CustomerBasket> customerBasketList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    CustomerBasket customerBasket = new CustomerBasket();
                    customerBasket.setId(resultSet.getInt("id"));
                    customerBasket.setCustomerId(id);
                    customerBasket.setProductId(resultSet.getInt("productId"));
                    customerBasket.setNumber(resultSet.getInt("numberProduct"));
                    customerBasket.setTotalPrice(resultSet.getDouble("price"));
                    customerBasketList.add(customerBasket);
                }
                return customerBasketList;
            } else
                return null;
    }

    public int findIdBy(int customerId,int productId,int numberProduct) throws SQLException {
            String findId = "SELECT id FROM CustomerBasket WHERE CustomerId = ? AND ProductId = ? AND numberProduct = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(findId);
            preparedStatement.setInt(1,customerId);
            preparedStatement.setInt(2,productId);
            preparedStatement.setInt(3,numberProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt("id");
            else
                return 0;
    }













}
