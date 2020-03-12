package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "idavaliacao")
	private int id;
	
	@Column(name = "nota")
	private int nota;
	
	@Column(name = "comentario")
	private String comentario;
	
	@Column(name = "fk_participante")
	private int participante;
	
	@Column(name = "fk_artigo")
	private int artigo;
	
}
