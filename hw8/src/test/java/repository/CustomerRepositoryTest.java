package repository;

import entity.Customer;
import entity.TypeUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    private final CustomerRepository customerRepository = new CustomerRepository();
    private static SessionFactory sessionFactory;
    private Session session;
    private final Customer customer = new Customer(0,"farzad","399","399", TypeUser.CUSTOMER,"Iran",10000D);

    @BeforeAll
    public static void testConnection() {
        sessionFactory = SessionFactorySingleton.getInstance();
        System.out.println("SessionFactory connected");
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @BeforeEach
    public void addAccount(){
        customerRepository.add(customer);
    }

    @Test
    public void testConnection1(){
        assertNotNull(sessionFactory);
    }

    @Test
    public void testAdd() {

        Customer customer1 = customerRepository.findById(customer.getId());
        assertAll(
                () -> assertNotEquals(0,customer1.getId()),
                () -> assertNotNull(customer1),
                () -> assertEquals("399",customer1.getNationalId())
        );
    }

    @Test
    public void testUpdate() {

        Customer customer1= new Customer(customer.getId(),"amin","499","499",TypeUser.CUSTOMER,"Iran",20000D);
        customerRepository.update(customer1);
        Customer customer2 = customerRepository.findById(customer1.getId());

        assertEquals("499",customer2.getNationalId());
    }

    @Test
    public void testFindById() {

        Customer customer1 =  customerRepository.findById(customer.getId());

        assertNotNull(customer1);
        assertEquals("399",customer1.getNationalId());
    }












    /*
    @Test
    public void testDelete() {
       // accountRepository.add(account);

        customerRepository.delete(customer.getId());
        Customer customer1 = customerRepository.findById(customer.getId());

        assertNull(customer1);
    }
     */

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }


    @AfterAll
    public static void downConnection() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @AfterEach
    public void cleanUp() {
        customerRepository.delete(customer.getId());
    }


}