package repository;

import entity.Account;
import entity.enumoration.TypeAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.AccountRepository;
import repository.SessionFactorySingleton;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {
    private final AccountRepository accountRepository = new AccountRepository();
    private static SessionFactory sessionFactory;
    private Session session;
    Account account = new Account(null,"1111","1111111111","11111",111D, TypeAccount.ACTIVE);

    @BeforeAll
    public static void testConnection() {
        sessionFactory = SessionFactorySingleton.getInstance();
        System.out.println("SessionFactory connected");
    }

    @AfterAll
    public static void downConnection() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @AfterEach
    public void cleanUp() {
        accountRepository.delete(account);
    }


        @Test
    public void testConnection1(){
        assertNotNull(sessionFactory);
    }

    @BeforeEach
    public void addAccount(){
        accountRepository.add(account);
    }


    @Test
    public void testAdd() {

        Account account1 = accountRepository.findByAccountNumber(account.getAccountNumber());
        assertAll(
                () -> assertNotEquals(0,account1.getId()),
                () -> assertNotNull(account1),
                () -> assertEquals("1111",account1.getCodeBranch())
        );
    }

    @Test
    public void testUpdate() {

        Account account1 = new Account(account.getId(),"2222","2222222222","22222",222D,TypeAccount.ACTIVE);
        accountRepository.update(account1);
        Account account2 = accountRepository.findByAccountNumber(account1.getAccountNumber());

        assertEquals("2222",account2.getCodeBranch());
    }

    @Test
    public void testFindById() {

        String numberAccount =  accountRepository.findById(account.getId());

        assertEquals("11111",numberAccount);
    }

    @Test
    public void testList() {
    }

    @Test
    public void testFindByAccountNumber(){

        Account newAccount = accountRepository.findByAccountNumber(account.getAccountNumber());

        assertEquals(account,newAccount);
    }

    /*
    @Test
    public void testDelete() {
        accountRepository.add(account);

        accountRepository.delete(account);
        String account1 = accountRepository.findById(account.getId());

        assertNull(account1);
    }
     */

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }

}