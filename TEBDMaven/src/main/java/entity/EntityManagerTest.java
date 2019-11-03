/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pedido.Pedido;

/**
 *
 * @author aluno
 */
public class EntityManagerTest {
    private EntityManagerFactory entityManagerFactory;
    
    protected void setUp(){
        entityManagerFactory = Persistence.createEntityManagerFactory( "PersistenceUnitTeste" );
    }
    
    protected void tearDown() throws Exception {
        entityManagerFactory.close();
    }
    
    public void testBasicUsage() {
            // create a couple of events...
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist( new Pedido( 1 ,"Teste de inserção" ));
            entityManager.getTransaction().commit();
            entityManager.close();

            // now lets pull events from the database and list them
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Pedido> result = entityManager.createQuery( "from Event", Pedido.class ).getResultList();
            for ( Pedido pedido : result ) {
                    System.out.println( "Event (" + pedido.getId() + ") : " + pedido.getDescricao() );
            }
            entityManager.getTransaction().commit();
            entityManager.close();
    }
    
    public static void main(String[] args) throws Exception {
        EntityManagerTest test = new EntityManagerTest();
        test.setUp();
        test.testBasicUsage();
    }

    
}
