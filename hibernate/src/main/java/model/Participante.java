package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "participante")
public class Participante {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "idparticipante")
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "empresa")
	private String empresa;
	
	@Column(name = "numero_cartao")
	private String numeroCartao;
	
	@Column(name = "vencimento_cartao")
	private String vencimentoCartao;
	
	@Column(name = "bandeira_cartao")
	private String bandeiraCartao;
	
	@Column(name = "avaliador")
	private boolean avaliador;

	@ManyToOne
	@JoinColumn(name = "fk_congresso", nullable = false)
	private Congresso congresso;
	
	@OneToMany(mappedBy = "participante", targetEntity = Autor.class)
	private Set<Autor> autor;
	
	public Participante(String nome, String cpf, String endereco,
						String telefone, String email, String empresa,
						String numeroCartao, String vencimentoCartao,
						String bandeiraCartao, boolean avaliador, Congresso congresso) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.empresa = empresa;
		this.numeroCartao = numeroCartao;
		this.vencimentoCartao = vencimentoCartao;
		this.bandeiraCartao = bandeiraCartao;
		this.avaliador = avaliador;
		this.congresso = congresso;
	}
	
	public Participante() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getVencimentoCartao() {
		return vencimentoCartao;
	}

	public void setVencimentoCartao(String vencimentoCartao) {
		this.vencimentoCartao = vencimentoCartao;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}

	public boolean isAvaliador() {
		return avaliador;
	}

	public void setAvaliador(boolean avaliador) {
		this.avaliador = avaliador;
	}

	public Congresso getCongresso() {
		return congresso;
	}

	public void setCongresso(Congresso congresso) {
		this.congresso = congresso;
	}

	public Set<Autor> getAutor() {
		return autor;
	}

	public void setAutor(Set<Autor> autor) {
		this.autor = autor;
	}
	
}
