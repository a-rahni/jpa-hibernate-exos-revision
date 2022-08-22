package fr.m2i.dao;

import fr.m2i.exception.AccountDaoException;
import fr.m2i.model.Account;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

public class AccountDaoImpl implements AccountDao {
    private EntityManagerFactory emf;
    
    // code ajouté sans etre demandé
    private EntityManager entityManager;

    public AccountDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
        // code ajouté sans etre demandé
        entityManager = emf.createEntityManager();
    }

    @Override
    public void save(Account account) {
        //performWithinPersistenceContext(em -> em.persist(account));
       if (account == null) {
            System.out.println("L'objet account ne peut pas être null");
            return;
        }

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.persist(account);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new AccountDaoException("Une erreur est survenu lors de la création",e.getCause());
        }
        
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public Account findById(Long id) {
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo

        Account founded= entityManager.find(Account.class, id);
        if (founded == null) {
            System.out.println("Attention le compte avec l'id: " + id + " n'existe pas !");
        }
        return founded;
        
    }

    @Override
    public Account findByEmail(String email) {
        
        if (email == null) {
            System.out.println("L'email du compte n'est pas valide !");
            return null;
        }
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.email=:emailParam", Account.class);
        //Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.email=:emailParam");
        query.setParameter("emailParam", email);
        
        return query.getSingleResult();
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public List<Account> findAll() {
        
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a", Account.class);
       
        return query.getResultList();
         //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public void update(Account account) {
        
        if(account==null || account.getId()==null || account.getId()< 0L){
            System.out.println("");
        }
        
        Account accountToUpdate = entityManager.find(Account.class, account.getId());
        
        if(accountToUpdate == null){
            System.out.println("Le compte avec l'id:" + account.getId() + " n'existe pas");
            return;
        }
        
        //accountToUpdate.copy(account); // test unitaire attend une exception lors setter null attribut non null
        
        EntityTransaction tx = null;
        try{
            tx = entityManager.getTransaction();
            tx.begin();
            
            //entityManager.merge(accountToUpdate);
            entityManager.merge(account);
            
            tx.commit();
            
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new AccountDaoException("Une erreur est survenu lors de la modification",e.getCause());
  
        }
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public void remove(Account account) {
        
        if(account == null || account.getId()==null || account.getId()<0L){
            System.out.println("Le compte n'est pas valide !");
            return;
        }
        
        Account accountToDelete = findById(account.getId());
        if(accountToDelete == null){
           return;
        }
         EntityTransaction tx = null;
         try{
             tx = entityManager.getTransaction();
             tx.begin();
             entityManager.remove(accountToDelete);
             
             tx.commit();
             
         }catch(Exception e){
            System.out.println("Une erreur est survenu lors de la suppression");
            System.out.println("Exception message : " + e.getMessage());
            if(tx!=null){
                tx.rollback();
            }        
         }
        //throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }
    
    
    
//    private void performWithinPersistenceContext(Consumer<EntityManager> entityManagerConsumer) {
//        performReturningWithinPersistenceContext(entityManager -> {
//            entityManagerConsumer.accept(entityManager);
//            return null;
//        });
//    }
//
//    private <T> T performReturningWithinPersistenceContext(Function<EntityManager, T> entityManagerFunction) {
//        EntityManager entityManager = emf.createEntityManager();
//        entityManager.getTransaction().begin();
//        T result;
//        try {
//            result = entityManagerFunction.apply(entityManager);
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            throw new AccountDaoException("Error performing dao operation. Transaction is rolled back!", e);
//        } finally {
//            entityManager.close();
//        }
//        return result;
//    }
}


