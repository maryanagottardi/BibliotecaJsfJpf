/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
/**
 *
 * @author mari
 */
@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    private String matricula, nome, telefone;
    private int retiradas, atrasos;

     public Cliente() {        
    }
     
    /**
     * Construtor para inicializar cliente
     *
     * @param matricula identifica a matrícula de uma pessoa.
     * @param nome identifica o nome de uma pessoa.
     * @param telefone identifica telefone de uma pessoa.
     *
     */
    public Cliente(String matricula, String nome, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public int getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(int retiradas) {
        this.retiradas = retiradas;
    }

    public int getAtrasos() {
        return atrasos;
    }

    public void setAtrasos(int atrasos) {
        this.atrasos = atrasos;
    }

    
    
    
  @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
   
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    public boolean verificaCliente(String nome){
        return(this.nome.equals(nome));
    }


     /**
     * converte o objeto pessoa em String para mostrar suas informações
     * corretamente.
     *
     * @return a matricula, o nome e o telefone de uma pessoa já formatado em
     * String.
     */
    @Override
    public String toString() {
        return matricula + " - " + nome + ", " + telefone;
    }
    
}
