package nivelamento1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import classe.Congresso;
import classe.Nota;
import classe.Participante;

public class Colecao {

	private static SessionFactory factory;
	public static final int ADICIONAR = 1;
	public static final int CONSULTAR = 2;
	public static final int REMOVER = 3;
	
	public int criaMenuPrincipal(){
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar Congresso");
		System.out.println("2. Consultar Congresso");
		System.out.println("3. Remover Congresso");
		//System.out.println("4. Media geral");
		System.out.println("5. Sair do Programa");
		System.out.println("-------------------");
		int opcao = Console.readInt();;
		return opcao;
	}
	
	public void addCongresso() throws Exception {
		String opcao;
		do {
			System.out.println("Adicionar Congresso:");
			System.out.println("-------------------");
			System.out.print("Nome do Congresso:");
			String nome = Console.readLine();
			EventoBD evento = new EventoBD(); //EventoBD
			evento.addCongresso(Colecao.factory, nome);
			System.out.print("Deseja Adicionar mais um congresso? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);
	}
	
	public Set<Participante> addParticipante(Congresso congresso){
		String opcao;
		Set<Participante> participantes = new HashSet<Participante>();
		do
		{
			System.out.println("Adicionar Participantes:");
			System.out.println("-------------------");
			System.out.print("Nome:");
			String nome = Console.readLine();
			System.out.print("CPF:");
			String cpf = Console.readLine();
			System.out.print("Endereço:");
			String endereco = Console.readLine();
			System.out.print("Telefone:");
			String telefone = Console.readLine();
			System.out.print("E-mail:");
			String email = Console.readLine();
			System.out.print("Empresa:");
			String empresa = Console.readLine();
			System.out.print("Número Cartão:");
			String numeroCartao = Console.readLine();
			System.out.print("Vencimento Cartão:");
			String vencimentoCartao = Console.readLine();
			System.out.print("Bandeira Cartão:");
			String bandeiraCartao = Console.readLine();
			System.out.print("Avaliador: [0|1]");
			int n = Console.readInt();
			boolean avaliador = false;
			if(n > 0) {
				avaliador = true;
			}
		    Participante participante = new Participante(nome, cpf, endereco,
														 telefone, email, empresa,
														 numeroCartao, vencimentoCartao,
														 bandeiraCartao, avaliador, congresso);
		    participantes.add(participante);
			System.out.print("Deseja Adicionar mais um participante? [S|N]: ");
			opcao = Console.readLine();
		} while (opcao.compareTo("S") == 0);
		return participantes;
	}
	
	public static void main (String args[]) throws Exception {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		try {
			Colecao ce = new Colecao();
			int opcao;
			while((opcao = ce.criaMenuPrincipal()) != 5){
				if(opcao == Colecao.ADICIONAR) {
					ce.addCongresso();
				} else System.out.println("Escolha uma opcao correta.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
