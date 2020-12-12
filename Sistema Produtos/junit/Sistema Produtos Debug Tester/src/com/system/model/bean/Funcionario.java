/*
 * Classe responsavel pelo mapeamento da tabela Funcionario
 * Subclasse de Pessoa e Super
 */

package com.system.model.bean;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * Sistema Produtos/ Model / Bean / Funcionario
 * @author MRX
 * Version : 1.0.0
 */

@Entity
public class Funcionario {
    
    //#region - Colunas
    @Id
    private Integer matricula;
    
    @Column(nullable = false, length = 80)
    private String nome;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dt_nasc;
    
    @Column(nullable = false, length = 4)
    private String sexo;
    
    @Column(length = 15)
    private String telefone;
    
    @Column(nullable = false, length = 80)
    private String endereco;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dt_contratacao;
    
    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal salario;
    //#endregion

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(Date dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public Date getDt_contratacao() {
        return dt_contratacao;
    }

    public void setDt_contratacao(Date dt_contratacao) {
        this.dt_contratacao = dt_contratacao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    
}
