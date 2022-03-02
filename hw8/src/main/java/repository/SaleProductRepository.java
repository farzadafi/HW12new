package repository;

import entity.SaleProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleProductRepository implements Repository<SaleProduct> {
    private Connection connection;

    public SaleProductRepository() {
        try{
            connection = Singleton.getInstance().getConnection();
            String CreateTable = "CREATE TABLE IF NOT EXISTS SaleProduct(id serial PRIMARY KEY," +
                    "customerId integer REFERENCES UserTable(id)," +
                    "productId integer ," +
                    "numberProduct integer," +
                    "price DECIMAL," +
                    "dateFinal DATE)";
            PreparedStatement preparedStatement = connection.prepareStatement(CreateTable);
            preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public int add(SaleProduct saleProduct) {
        try {
            String add = "INSERT INTO SaleProduct(customerId,productId,numberProduct,price,dateFinal) VALUES (?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setInt(1,saleProduct.getCustomerId());
            preparedStatement.setInt(2,saleProduct.getProductId());
            preparedStatement.setInt(3,saleProduct.getNumber());
            preparedStatement.setDouble(4,saleProduct.getTotalPrice());
            preparedStatement.setDate(5,saleProduct.getDate());
            return preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public List<SaleProduct> saleProductByCustomerId(int id){
        try {
            String findAll = "SELECT * FROM SaleProduct WHERE customerId = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            preparedStatement.setInt(1,id);
            List<SaleProduct> saleProductList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    SaleProduct saleProduct = new SaleProduct();
                    saleProduct.setId(resultSet.getInt("id"));
                    saleProduct.setCustomerId(resultSet.getInt("customerId"));
                    saleProduct.setProductId(resultSet.getInt("productId"));
                    saleProduct.setNumber(resultSet.getInt("numberProduct"));
                    saleProduct.setTotalPrice(resultSet.getDouble("price"));
                    saleProduct.setDate(resultSet.getDate("dateFinal"));
                    saleProductList.add(saleProduct);
                }
                return saleProductList;
            } else
                return null;
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public List<SaleProduct> saleProductByAdminId(int id) throws SQLException {
            String find = "SELECT  saleproduct.id,\n" +
                    "        customerid,\n" +
                    "        productid,\n" +
                    "        saleproduct.numberproduct,\n" +
                    "        saleproduct.price,\n" +
                    "        datefinal\n" +
                    "        FROM SaleProduct\n" +
                    "        INNER JOIN Product ON productId = product.id WHERE adminid = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            preparedStatement.setInt(1, id);
            List<SaleProduct> saleProductList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    SaleProduct saleProduct = new SaleProduct();
                    saleProduct.setId(resultSet.getInt("id"));
                    saleProduct.setCustomerId(resultSet.getInt("customerId"));
                    saleProduct.setProductId(resultSet.getInt("productId"));
                    saleProduct.setNumber(resultSet.getInt("numberProduct"));
                    saleProduct.setTotalPrice(resultSet.getDouble("price"));
                    saleProduct.setDate(resultSet.getDate("dateFinal"));
                    saleProductList.add(saleProduct);
                }
                return saleProductList;
            } else
                return null;
    }


        @Override
    public List<SaleProduct> findAll() {
            try {
                String findAll = "SELECT * FROM SaleProduct" ;
                PreparedStatement preparedStatement = connection.prepareStatement(findAll);
                List<SaleProduct> saleProductList = new ArrayList<>();
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        SaleProduct saleProduct = new SaleProduct();
                        saleProduct.setId(resultSet.getInt("id"));
                        saleProduct.setCustomerId(resultSet.getInt("customerId"));
                        saleProduct.setProductId(resultSet.getInt("productId"));
                        saleProduct.setNumber(resultSet.getInt("numberProduct"));
                        saleProduct.setTotalPrice(resultSet.getDouble("price"));
                        saleProduct.setDate(resultSet.getDate("dateFinal"));
                        saleProductList.add(saleProduct);
                    }
                    return saleProductList;
                } else
                    return null;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
            return null;
    }

    @Override
    public int update(SaleProduct saleProduct) {
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            String del = "DELETE FROM SaleProduct WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(del);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }
}
