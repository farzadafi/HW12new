package repository;

import entity.Account;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class AccountRepository implements Repository<Account> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public int add(Account account){
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
                return account.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public String findById(int id){
        try (var session = sessionFactory.openSession()) {
            Account account = session.find(Account.class,id);
            if(account == null )
                return null;
            else
                return account.getAccountNumber();
        }
    }

    public void update(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public void delete(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }


    public Account findByAccountNumber(String accountNumber) {
        try (var session = sessionFactory.openSession()) {
            NativeQuery query = session.createSQLQuery("SELECT * FROM Account WHERE accountnumber = :accountNumber");
            query.addEntity(Account.class);
            query.setParameter("accountNumber",accountNumber);
            List account = null;
            try {
                account = query.list();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if( account == null || account.size() == 0 ) {
                return null;
            }
            else {
                return (Account) account.get(0);
            }
        }
    }

    public int find(String input){
        return 0;
    }


    public List<Account> showAllAccount(String nationalId){
        try (var session = sessionFactory.openSession()) {
            NativeQuery query = session.createSQLQuery("SELECT * FROM Account WHERE nationalid = :nationalId");
            query.addEntity(Account.class);
            query.setParameter("nationalId", nationalId);
            List account = null;
            try {
                account = query.list();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return account;
        }
    }

}




