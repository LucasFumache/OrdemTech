package br.com.usandoapi.ordemtech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")


public class OrdemTech {

	// especificar a chave primaria
			@Id
			// essa anotation informa que o campo é auto incremento
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			@Column(name = "id")
			private Integer id;
			
			@Column(name = "nome", length = 200, nullable = true)
			private String nome;
			
			@Column(name = "cpf", length = 15, nullable = true)
			private String cpf;
			
			@Column(name = "telefone", length = 15, nullable = true)
			private String telefone;
			
			@Column(name = "endereco", length = 200, nullable = true)
			private String endereco;
			
			@Column(name = "senha", columnDefinition = "TEXT", nullable = true)
			private String senha;

			public Integer getId() {
				return id;
			}

			public String getCpf() {
				return cpf;
			}

			public void setCpf(String cpf) {
				this.cpf = cpf;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public String getNome() {
				return nome;
			}

			public void setNome(String nome) {
				this.nome = nome;
			}

			public String getTelefone() {
				return telefone;
			}

			public void setTelefone(String telefone) {
				this.telefone = telefone;
			}

			public String getEndereco() {
				return endereco;
			}

			public void setEndereco(String endereco) {
				this.endereco = endereco;
			}

			public String getSenha() {
				return senha;
			}

			public void setSenha(String senha) {
				this.senha = senha;
			}
			
			
	
}
