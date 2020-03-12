package nivelamento1;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import classe.Artigo;
import classe.Congresso;
import classe.Participante;

public class EventoBD {
	
	public void addCongresso(SessionFactory factory, String nome) throws Exception{
		Congresso congresso;
		Set<Participante> participantes = new HashSet<Participante>();
		Session session  = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			congresso = new Congresso(nome);
			
			Colecao colecao = new Colecao();
			participantes = colecao.addParticipante(congresso);
			congresso.setParticipante(participantes);
			
			session.save(congresso);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		}
	}
	
	public Congresso getCongresso() throws Exception {
		Congresso congresso = new Congresso();
		
		return congresso;
	}
	
	public void addParticipante(SessionFactory factory,
								String nome, String cpf, String endereco,
								String telefone, String email, String empresa,
								String numeroCartao, String vencimentoCartao,
								String bandeiraCartao, boolean avaliador, Congresso congresso) throws Exception{
		Participante participante;
		Session session  = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			participante = new Participante(nome, cpf, endereco, telefone, email,
											empresa, numeroCartao, vencimentoCartao,
											bandeiraCartao, avaliador, congresso);
			session.save(participante);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		}
	}
	
	public void addArtigo(SessionFactory factory, String titulo, String resumo) throws Exception {
		Artigo artigo;
		Session session  = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			artigo = new Artigo(titulo, resumo);
			session.save(artigo);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		}
	}
	
}
