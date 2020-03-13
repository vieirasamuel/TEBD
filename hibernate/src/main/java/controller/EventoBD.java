package controller;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Artigo;
import model.Autor;
import model.Congresso;
import model.HibernateUtil;
import model.Participante;
import view.Colecao;

public class EventoBD {
	
	public void addCongresso(Session session, String nome) throws Exception{
		Congresso congresso;
		Set<Participante> participantes = new HashSet<Participante>();
		//Session session  = factory.openSession();
		//Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			congresso = new Congresso(nome);
			
			Colecao colecao = new Colecao();
			session.save(congresso);
			tx.commit();
			participantes = colecao.addParticipante(session, congresso);
			congresso.setParticipante(participantes);
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close(); 
			//session.clear();
		}
	}
	
	public Congresso getCongresso() throws Exception {
		Congresso congresso = new Congresso();
		
		return congresso;
	}
	
	public void addParticipante(Participante participante) throws Exception {
//			String nome, String cpf, String endereco,
//								String telefone, String email, String empresa,
//								String numeroCartao, String vencimentoCartao,
//								String bandeiraCartao, boolean avaliador, Congresso congresso) throws Exception{
		//Participante participante;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			participante = new Participante(nome, cpf, endereco, telefone, email,
//											empresa, numeroCartao, vencimentoCartao,
//											bandeiraCartao, avaliador, congresso);
			session.save(participante);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
			//session.clear();
		}
	}
	
	public void addArtigo(Session session, String titulo, String resumo) throws Exception {
		Artigo artigo;
		//Session session = HibernateUtil.getSession();
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
			//session.close(); 
			//session.clear();
		}
	}
	public void addAutor(Session session, Autor autor) {
		//Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(autor);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			//session.close();
		}
	}
	
}
