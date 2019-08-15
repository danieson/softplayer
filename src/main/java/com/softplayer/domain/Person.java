package com.softplayer.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Person {
    @Id
    @NotNull(message = "CPF não pode ser nulo")
	@NotEmpty(message = "CPF não pode ser nulo")
	private String cpf;
    @NotNull(message = "Nome não pode ser nulo")
	@NotEmpty(message = "Nome não pode ser nulo")
	private String nome;
	private String sexo;
	private String email;
	private String naturalidade;
	private String nacionalidade;
    @NotNull(message = "Data Nascimento não pode ser nulo")
	@NotEmpty(message = "Data Nascimento não pode ser nulo")
	private String dataNascimento;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
    private Date dataCadastro;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
    private Date dataAtualizacao;
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
