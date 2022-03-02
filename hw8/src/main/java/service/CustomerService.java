package service;

import entity.Customer;
import repository.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements Service<Customer> {
    CustomerRepository customerRepository = new CustomerRepository();


    @Override
    public int add(Customer customer) {
        try {
            return customerRepository.add(customer);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public int update(Customer customer) {
        try {
            return customerRepository.update(customer);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    public int addBudget(int id,Double amount){
        try {
            return customerRepository.addBudget(id,amount);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public Double returnBudget(int id){
        try {
            return customerRepository.returnBudget(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return  0d;
    }






}
