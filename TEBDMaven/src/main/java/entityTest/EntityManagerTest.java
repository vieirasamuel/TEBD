/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityTest;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import teste.Aluno;
import teste.Nota;

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
    
    public void adicionaAluno(Nota nota){
    }
    
    public void adicionaNota(Aluno aluno,Nota nota){
    }
    
    public void testBasicUsage() {
            Aluno jv = new Aluno("123","JV");
            List<Nota> notas = new ArrayList<Nota>();
            Nota n1 = new Nota(1,jv,0);
            Nota n2 = new Nota(2,jv,1);
            Nota n3 = new Nota(3,jv,2);
            notas.add(n1);            
            notas.add(n2);
            notas.add(n3);
            jv.setNota(notas);
            

            // create a couple of events...
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(jv);
            entityManager.getTransaction().commit();
            entityManager.close();

            // now lets pull events from the database and list them
            /*entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Pedido> result = entityManager.createQuery( "from Pedido", Pedido.class ).getResultList();
            for ( Pedido pedido : result ) {
                    System.out.println( "PEDIDO (" + pedido.getId() + ") : " + pedido.getDescricao() );
            }
            entityManager.getTransaction().commit();
            entityManager.close();*/
    }
    
    public static void main(String[] args) throws Exception {
        EntityManagerTest test = new EntityManagerTest();
        test.setUp();
        test.testBasicUsage();
    }

    
}
