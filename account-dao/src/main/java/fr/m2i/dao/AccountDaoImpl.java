package fr.m2i.dao;

import fr.m2i.exception.AccountDaoException;
import fr.m2i.model.Account;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AccountDaoImpl implements AccountDao {
    private EntityManagerFactory emf;
    
    // code ajouté sans etre demandé
    //private EntityManager entityManager;

    public AccountDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
        // code ajouté sans etre demandé
        //entityManager = emf.createEntityManager();
    }

    @Override
    public void save(Account account) {
        performWithinPersistenceContext(em -> em.persist(account));
//       if (account == null) {
//            System.out.println("L'objet account ne peut pas être null");
//            return;
//        }
//
//        EntityTransaction tx = null;
//
//        try {
//            tx = entityManager.getTransaction();
//            tx.begin();
//
//            entityManager.persist(account);
//
//            tx.commit();
//        } catch (Exception e) {
//            System.out.println("Une erreur est survenu lors de la création");
//            System.out.println("Exception message : " + e.getMessage());
//            if (tx != null) {
//                tx.rollback();
//            }
//        }
        
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public Account findById(Long id) {
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
        return null;
    }

    @Override
    public Account findByEmail(String email) {
        throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public List<Account> findAll() {
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
        return null;
    }

    @Override
    public void update(Account account) {
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public void remove(Account account) {
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }
    
    
    
    private void performWithinPersistenceContext(Consumer<EntityManager> entityManagerConsumer) {
        performReturningWithinPersistenceContext(entityManager -> {
            entityManagerConsumer.accept(entityManager);
            return null;
        });
    }

    private <T> T performReturningWithinPersistenceContext(Function<EntityManager, T> entityManagerFunction) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        T result;
        try {
            result = entityManagerFunction.apply(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new AccountDaoException("Error performing dao operation. Transaction is rolled back!", e);
        } finally {
            entityManager.close();
        }
        return result;
    }
}


