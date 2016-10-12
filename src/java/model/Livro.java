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
/**
 *
 * @author mari
 */
@Entity
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private static int CODIGO_GERADO = 1;
    private String isbn, nome, autor, editora, ano;
    private int retiradas;

 public Livro(String isbn, String nome, String autor, String editora, String ano) {
        this.isbn = isbn;
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;        
        this.ano = ano;
    }
  public Livro(String nome, String autor, String editora, String ano) {
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;        
        this.ano = ano;
    }
  
    public Livro() {
        
    }
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public static int getCODIGO_GERADO() {
        return CODIGO_GERADO;
    }

    public static void setCODIGO_GERADO(int CODIGO_GERADO) {
        Livro.CODIGO_GERADO = CODIGO_GERADO;
    }
    
     private int generateCodigo() {
        return (CODIGO_GERADO++);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(int retiradas) {
        this.retiradas = retiradas;
    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
     public String toString() {
        return "Livro{" + "isbn=" + isbn + ", nome=" + nome + ", autor=" + autor + ", editora=" + editora + ", ano=" + ano + '}';
    }
    
}
