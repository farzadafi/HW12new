package repository;

import entity.Customer;
import entity.TypeUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

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

    /*
    @AfterEach
    public void cleanUp() {
        accountRepository.delete(account);
    }
     */


}