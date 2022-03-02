package repository;

import entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.sql.Connection;
import java.util.List;

public class CustomerRepository implements Repository<Customer> {
    private final SessionFactory sessionFactory;

    public CustomerRepository(){
        sessionFactory = SessionFactorySingleton.getInstance();
    }

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

    @Override
    public int delete(int id) {
        Customer customerDelete = findById(id);
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(customerDelete);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

        return 1;
    }

    public Customer findById(int id){
        try (var session = sessionFactory.openSession()) {
            Customer customer = session.find(Customer.class,id);
            return customer;
        }
    }
}
