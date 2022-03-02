package service;

import entity.Customer;
import repository.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements Service<Customer> {
    CustomerRepository customerRepository = new CustomerRepository();


    @Override
    public int add(Customer customer) {
        return customerRepository.add(customer);
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public int update(Customer customer) {
            return customerRepository.update(customer);
    }

    @Override
    public int delete(int id) {
        return 0;
    }


    public Customer findById(int id){
        return customerRepository.findById(id);
    }
}
