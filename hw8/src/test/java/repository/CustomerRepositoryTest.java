package repository;

import entity.Customer;
import entity.TypeUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

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

    @Test
    public void testList() {
        Customer customer1 = new Customer(0,"farzad1","499","499", TypeUser.CUSTOMER,"Iran",20000D);
        Customer customer2 = new Customer(0,"farzad2","599","599", TypeUser.CUSTOMER,"Iran",30000D);

        customerRepository.add(customer1);
        customerRepository.add(customer2);

        List<Customer> customerList = customerRepository.findAll();
        customerRepository.delete(customer1.getId());
        customerRepository.delete(customer2.getId());

        if(customerList.size() < 3 )
            fail();
    }

    @Test
    public void testDelete() {
        Customer customer1 = new Customer(0,"farzad1","499","499", TypeUser.CUSTOMER,"Iran",20000D);
        customerRepository.add(customer1);

        customerRepository.delete(customer1.getId());
        Customer customer2 = customerRepository.findById(customer1.getId());

        assertNull(customer2);
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

    @AfterEach
    public void cleanUp() {
        customerRepository.delete(customer.getId());
    }


}