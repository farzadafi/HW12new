package service;

import entity.CustomerBasket;
import repository.CustomerBasketRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerBasketService implements Service<CustomerBasket> {
    private CustomerBasketRepository customerBasketRepository = new CustomerBasketRepository();


    @Override
    public int add(CustomerBasket customerBasket) {
        try {
            return customerBasketRepository.add(customerBasket);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<CustomerBasket> findAll() {
        return null;
    }

    @Override
    public int update(CustomerBasket customerBasket) {
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return customerBasketRepository.delete(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public List<CustomerBasket> customerBasketById(int id){
        try {
            return customerBasketRepository.customerBasketById(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public int findIdBy(int customerId,int productId,int numberProduct){
        try {
            return customerBasketRepository.findIdBy(customerId,productId,numberProduct);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }
}
