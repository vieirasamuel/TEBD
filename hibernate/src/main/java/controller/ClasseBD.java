package controller;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Aluno;
import model.Nota;
import view.ColecaoClasse;

public class ClasseBD {
	

	
	public List<Nota> mediaAll(SessionFactory factory) {
				
		Session session = factory.openSession();
		Transaction tx = null;   
		try {
			tx = session.beginTransaction();
			List notas = session.createQuery("FROM Nota").list();
			tx.commit();
			return notas;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return null;
	}
	
	public Aluno listAluno(SessionFactory factory, String matricula) {
		
		Session session = factory.openSession();
		Transaction tx = null;
	      
		try {
			tx = session.beginTransaction();
			Aluno aluno = (Aluno)session.get(Aluno.class, matricula);
			tx.commit();
			return aluno;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return null;
	}
	
	public void removeAluno(SessionFactory factory, String matricula) {
		Session session = factory.openSession();
		Transaction tx = null;
	      
		try {
			tx = session.beginTransaction();
			Aluno aluno = (Aluno)session.get(Aluno.class, matricula); 
			session.delete(aluno); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	
	public void addAluno(SessionFactory factory,String matricula, String nome) throws Exception {
		Aluno aluno;
		Set<Nota> notas = new HashSet<Nota>();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			aluno = new Aluno(matricula,nome);
			
			ColecaoClasse cc = new ColecaoClasse();
			notas = cc.addNotas(aluno);			 
			
			aluno.setNota(notas);
			session.save(aluno);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

}
