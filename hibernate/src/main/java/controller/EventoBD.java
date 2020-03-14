package controller;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Artigo;
import model.Autor;
import model.Avaliacao;
import model.Congresso;
import model.Participante;
import view.Colecao;

public class EventoBD {
	
	public void addCongresso(Session session, String nome) throws Exception{
		Congresso congresso;
		Set<Participante> participantes = new HashSet<Participante>();
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
		}
	}
	
	public void addParticipante(Session session, Participante participante) throws Exception {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(participante);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}
	
	public void addArtigo(Session session, String titulo, String resumo) throws Exception {
		Artigo artigo;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			artigo = new Artigo(titulo, resumo);
			session.save(artigo);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}
	
	public void addAutor(Session session, Autor autor) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(autor);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}
	
	public void addAvaliacao(Session session, Avaliacao avaliacao) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(avaliacao);
			tx.commit();
		} catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}
	
}
