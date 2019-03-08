package org.controle.servicos.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class OrdemServico implements Serializable {
	
	private static final long serialVersionUID = 7166566746379362785L;
	
	@Id @Column @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne @NotNull  
	private Cliente cliente;
	@NotNull
	@ManyToMany
    @JoinTable(name="servico_ordemservico", 
    		   joinColumns = { @JoinColumn(name="Servico_id") }, 
    		   inverseJoinColumns = { @JoinColumn(name="ordens_id")}
    )	
	private List<Servico> servicos;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEncerramento;
	@Column
	private Situacao situacao;	
	
	public OrdemServico(){
		
	}	

	public OrdemServico(Long id, Cliente cliente, List<Servico> servicos, Date dataAbertura, Date dataEncerramento,
			Situacao situacao) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.servicos = servicos;
		this.dataAbertura = dataAbertura;
		this.dataEncerramento = dataEncerramento;
		this.situacao = situacao;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Servico> getServico() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	

}
