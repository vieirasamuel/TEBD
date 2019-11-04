package com.mycompany.tebdmaven;

import java.util.*;
import java.sql.*;
import entityTest.EntityManagerTest;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import teste.Aluno;
import teste.Nota;

public  class ColecaoAluno{
    
	public static final int ADICIONAR = 1;
	public static final int CONSULTAR = 2;
	public static final int REMOVER = 3;
	private EntityManagerFactory entityManagerFactory;
/**
* Metodo Construtor que gera uma conexao com o Banco de Dados
*/
    public ColecaoAluno() throws Exception{

    }
/**
* Metodo que monta o menu principal e 
* obtem uma opcao do usuario
*/
        protected void setUp(){
            entityManagerFactory = Persistence.createEntityManagerFactory( "PersistenceUnitTeste" );
        }
	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar aluno");
		System.out.println("2. Consultar aluno");
		System.out.println("3. Remover aluno  ");
		System.out.println("4. Sair do Programa");
		System.out.println("-------------------");
		return opcao = Console.readInt();
	}
        
        public void adicionarAlunoDB(Aluno aln){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(aln);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        
        public void removerAlunoDB(String matricula){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Aluno aluno = entityManager.find(Aluno.class, matricula);
            entityManager.remove(aluno);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        
        public void consultarAlunoDB(String matricula){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Aluno aluno = entityManager.find(Aluno.class, matricula);
            /*List<Aluno> result = entityManager.createQuery( "from Aluno", Aluno.class ).getResultList();
            for ( Aluno aluno : result ) {
                    System.out.println( "Matricula (" + aluno.getMatricula() + ") : " + aluno.getNome());
                    System.out.println("Notas:");
                    System.out.println("------------------------");
                    for(Nota nota : aluno.getNota() ){
                        System.out.println(nota.getNota());
                    }
            }*/
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println(aluno.getNome());
        }
        
/**
* Metodo que adiciona itens relacionado com o seu pedido
*/	
	public List<Nota> AdicionarNotas(Aluno aln) throws Exception {
		String opcao;
                List<Nota> notas = new ArrayList<Nota>();
		do
		{
			System.out.println("Adicionar Notas:");
			System.out.println("-------------------");
			System.out.print("Valor da nota:");
			float nota = (float) Console.readDouble();
                        Nota n = new Nota(aln,nota);
                        notas.add(n);
			System.out.print("Deseja Adicionar mais uma nota? [S|N]: ");
			opcao = Console.readLine();
		} while (opcao.compareTo("S") == 0);
                return notas;
	}
	

/**
* 
*/	
	public void AdicionarAluno()throws Exception{
		Aluno aln;
                List<Nota> notas = new ArrayList<Nota>();
		String opcao;
		do {
			System.out.println("Adicionar Pedido:");
			System.out.println("-------------------");
			System.out.print("Matricula do aluno:");
			String matricula = Console.readLine();
			System.out.print("Nome do aluno:");
			String nome = Console.readLine();
			
						
			aln = new Aluno(matricula, nome);
			
			notas = this.AdicionarNotas(aln);
                        aln.setNota(notas);
                        
                        adicionarAlunoDB(aln);

						
			System.out.print("Deseja Adicionar mais um Aluno? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);

	}
	
	
	
	
	public void RemoverAluno()throws Exception{
		int opcao;
		System.out.println("Remover Pedido:");
		System.out.println("-------------------");
		System.out.print("Digite a matricula do aluno:");
		String matricula = Console.readLine();
		removerAlunoDB(matricula);
	}
	

	
	public void ConsultarAluno() throws Exception{
		int opcao;
		System.out.println("Consultar aluno:");
		System.out.println("-------------------");
		System.out.print("Digite a matricula do aluno:");
		String matricula = Console.readLine();;
		
		consultarAlunoDB(matricula);
	
	}
	
	public static void main(String args[]) throws Exception{
		try {
			ColecaoAluno cp = new ColecaoAluno();
                        cp.setUp();
			int opcao;
			while((opcao = cp.criaMenuPrincipal()) != 4){
				if(opcao == ColecaoAluno.ADICIONAR)
					cp.AdicionarAluno();
				else if(opcao == ColecaoAluno.CONSULTAR)
					cp.ConsultarAluno();
				else if(opcao == ColecaoAluno.REMOVER)
					cp.RemoverAluno();
				else System.out.println("Escolha uma opcao correta.");
			}
		}catch(Exception e){
			e.printStackTrace();
			}	
	}
}