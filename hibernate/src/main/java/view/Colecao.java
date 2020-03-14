package view;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import controller.EventoBD;
import model.Artigo;
import model.Autor;
import model.Avaliacao;
import model.Congresso;
import model.HibernateUtil;
import model.Participante;

public class Colecao {

	public static final int ADICIONAR = 1;
	public static final int ADICIONAR_AVALIACAO = 2;
	public static final int REMOVER = 3;
	
	public int criaMenuPrincipal(){
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar Congresso");
		System.out.println("2. Adicionar Avaliação");
		System.out.println("5. Sair do Programa");
		System.out.println("-------------------");
		int opcao = Console.readInt();;
		return opcao;
	}
	
	public void addCongresso(Session session) throws Exception {
		String opcao;
		do {
			System.out.println("Adicionar Congresso:");
			System.out.println("-------------------");
			System.out.print("Nome do Congresso:");
			String nome = Console.readLine();
			EventoBD evento = new EventoBD();
			evento.addCongresso(session, nome);
			System.out.print("Deseja Adicionar mais um congresso? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);
	}
	
	public Set<Participante> addParticipante(Session session, Congresso congresso) throws Exception{
		String opcao, opcao2;
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
		    EventoBD evento = new EventoBD();
			evento.addParticipante(session, participante);
		    System.out.print("O participante é um autor? [S|N]: ");
			opcao = Console.readLine();
			if(opcao.compareTo("S") == 0) {
				Artigo artigo = addArtigo(session);
				participante.setAutor(addAutor(session, participante, artigo));
			}
			System.out.print("Deseja Adicionar mais um participante? [S|N]: ");
			opcao2 = Console.readLine();
		} while (opcao2.compareTo("S") == 0);
		return participantes;
	}
	
	public Set<Autor> addAutor(Session session, Participante participante, Artigo artigo) throws Exception {
		String opcao;
		Set<Autor> autores = new HashSet<Autor>();
		do {
			
			Autor autor = new Autor(participante, artigo);
			EventoBD evento = new EventoBD();
			evento.addAutor(session, autor);
			autores.add(autor);
			System.out.print("Quer adicionar mais um artigo? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);
		return autores;
	}
	
	public Artigo addArtigo(Session session) throws Exception {
		Artigo artigo = new Artigo();
		System.out.println("Cadastro de Artigo:");
		System.out.println("-------------------");
		System.out.print("Titulo:");
		String titulo = Console.readLine();
		System.out.print("Resumo:");
		String resumo = Console.readLine();
		artigo.setTitulo(titulo);
		artigo.setResumo(resumo);
		return artigo;
	}
	
	public void addAvaliacao(Session session) throws Exception {
		System.out.println("Avaliação de Artigo:");
		System.out.println("-------------------");
		System.out.print("Digite o ID do Participante:");
		int idParticipante = Console.readInt();
		System.out.print("Digite o ID do Artigo:");
		int idArtigo = Console.readInt();
		System.out.print("Nota:");
		String nota = Console.readLine();
		System.out.print("Comentário:");
		String comentario = Console.readLine();
		Participante participante = new Participante(idParticipante);
		Artigo artigo = new Artigo(idArtigo);
		Avaliacao avaliacao = new Avaliacao(nota, comentario, artigo, participante);
	    EventoBD evento = new EventoBD();
		evento.addAvaliacao(session, avaliacao);
	}
	
	public static void main (String args[]) throws Exception {
		Session session = HibernateUtil.getSession();
		try {
			Colecao ce = new Colecao();
			int opcao;
			while((opcao = ce.criaMenuPrincipal()) != 5){
				if(opcao == Colecao.ADICIONAR) {
					ce.addCongresso(session);
				} else if(opcao == Colecao.ADICIONAR_AVALIACAO) {
					ce.addAvaliacao(session);
				} else System.out.println("Escolha uma opcao correta.");
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
