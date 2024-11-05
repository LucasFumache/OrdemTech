package br.com.usandoapi.ordemtech.model;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordem_de_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_os")
    private Timestamp data_os;

    @Column(name = "status_servico", length = 40, nullable = true)
    private String status_servico;

    @Column(name = "situacao", length = 40, nullable = true)
    private String situacao;

    @Column(name = "aparelho", length = 40, nullable = true)
    private String aparelho;

    @Column(name = "defeito", length = 30, nullable = true)
    private String defeito;

    @Column(name = "nome_tecnico", length = 40, nullable = true)
    private String nome_tecnico;

    @Column(name = "data_de_entrega", columnDefinition = "DATE", nullable = true)
    private Date data_de_entrega; 

    @Column(name = "valortotal", length = 30, nullable = true)
    private String valortotal;

    @ManyToOne
    @JoinColumn(name = "id_cliente") 
    private OrdemTech cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_tecnico") 
    private OrdemTech tecnico;

    public OrdemTech getTecnico() {
		return tecnico;
	}

	public void setTecnico(OrdemTech tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getData_os() {
        return data_os;
    }

    public void setData_os(Timestamp data_os) {
        this.data_os = data_os;
    }

    public String getStatus_servico() {
        return status_servico;
    }

    public void setStatus_servico(String status_servico) {
        this.status_servico = status_servico;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getAparelho() {
        return aparelho;
    }

    public void setAparelho(String aparelho) {
        this.aparelho = aparelho;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getNome_tecnico() {
        return nome_tecnico;
    }

    public void setNome_tecnico(String nome_tecnico) {
        this.nome_tecnico = nome_tecnico;
    }

    public Date getData_de_entrega() {
        return data_de_entrega;
    }

    public void setData_de_entrega(Date data_de_entrega) {
        this.data_de_entrega = data_de_entrega;
    }

    public String getValortotal() {
        return valortotal;
    }

    public void setValortotal(String valortotal) {
        this.valortotal = valortotal;
    }

	public OrdemTech getCliente() {
		return cliente;
	}

	public void setCliente(OrdemTech cliente) {
		this.cliente = cliente;
	}

    
}
