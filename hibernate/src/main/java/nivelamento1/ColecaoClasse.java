package nivelamento1;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import classe.Aluno;
import classe.Nota;

public class ColecaoClasse {
	
	private static SessionFactory factory;
	public static final int ADICIONAR = 1;
	public static final int CONSULTAR = 2;
	public static final int REMOVER = 3;
	public static final int CONSULTAR_MEDIA = 4;
	
	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar Aluno");
		System.out.println("2. Consultar Aluno");
		System.out.println("3. Remover Aluno  ");
		System.out.println("4. Media geral");
		System.out.println("5. Sair do Programa");
		System.out.println("-------------------");
		return opcao = Console.readInt();
	}
	
	public Set<Nota> addNotas(Aluno aluno) throws Exception {
		String opcao;
		Set<Nota> notas = new HashSet<Nota>();
		do
		{
			System.out.println("Adicionar Notas:");
			System.out.println("-------------------");
			System.out.print("Valor:");
			double n = Console.readDouble();
		    Nota nota = new Nota(aluno,n);
		    notas.add(nota);
			System.out.print("Deseja Adicionar mais uma nota? [S|N]: ");
			opcao = Console.readLine();
		} while (opcao.compareTo("S") == 0);
		return notas;
	}
	
	public void addAluno() throws Exception{
		String opcao;
		do {
			System.out.println("Adicionar Aluno:");
			System.out.println("-------------------");
			System.out.print("Matricula do aluno:");
			String matricula = Console.readLine();
			System.out.print("Nome do aluno:");
			String nome = Console.readLine();
			ClasseBD classe = new ClasseBD();
			classe.addAluno(ColecaoClasse.factory, matricula, nome);
			System.out.print("Deseja Adicionar mais um aluno? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);
	}
	
	public void removeAluno() throws Exception{
		System.out.println("Remover Aluno:");
		System.out.println("-------------------");
		System.out.print("Digite a matricula do aluno:");
		String matricula = Console.readLine();
		ClasseBD classe = new ClasseBD();
		classe.removeAluno(ColecaoClasse.factory, matricula);
	}
	
	
	public void listAluno() throws Exception{
		double media = 0;
		int qtdNotas = 1;
		System.out.println("Consultar Aluno:");
		System.out.println("-------------------");
		System.out.print("Digite a matricula do aluno:");
		String matricula = Console.readLine();
		ClasseBD classe = new ClasseBD();
		Aluno aluno = classe.listAluno(ColecaoClasse.factory, matricula);
		System.out.println("Matricula: " + aluno.getMatricula()); 
		System.out.println("Nome: " + aluno.getNome()); 
		System.out.println("Notas:");
		qtdNotas = aluno.getNota().size();
		for(Nota nota :aluno.getNota()) {
			media += nota.getNota();
			System.out.println(nota.getNota());

		}
		media /= qtdNotas;
		System.out.println("Media");
		System.out.println("-------------------");
		System.out.println(media);
		System.out.println("-------------------");

	}
	
	public void mediaAll() throws Exception{
		double media = 0;
		int qtdNotas = 1;
		System.out.println("Media geral:");
		System.out.println("-------------------");
		ClasseBD classe = new ClasseBD();
		List notas = classe.mediaAll(ColecaoClasse.factory);
		qtdNotas = notas.size();
		for (Iterator iterator = notas.iterator(); iterator.hasNext();){
			Nota nota = (Nota) iterator.next();
			System.out.println("Notas:" + nota.getNota());
			media += nota.getNota();
		}
		media /= qtdNotas;
		System.out.println(media);
	}
	
	public static void main(String args[]) throws Exception{
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		try {
			ColecaoClasse ce = new ColecaoClasse();
			int opcao;
			while((opcao = ce.criaMenuPrincipal()) != 5){
				if(opcao == ColecaoClasse.ADICIONAR)
					ce.addAluno();
				else if(opcao == ColecaoClasse.CONSULTAR)
					ce.listAluno();
				else if(opcao == ColecaoClasse.REMOVER)
					ce.removeAluno();
				else if(opcao == ColecaoClasse.CONSULTAR_MEDIA)
					ce.mediaAll();
				else System.out.println("Escolha uma opcao correta.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
