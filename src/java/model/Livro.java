
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private boolean disponivel = true;
    @Temporal (value=TemporalType.DATE)
    private Date dataLiberacao = new Date(System.currentTimeMillis()); 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Construtor para inicializar livro
     */        
    public Livro(){
        
    }

    /**
     * Retorna codigo do menu
    
     */
    public static int getCODIGO_GERADO() {
        return CODIGO_GERADO;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * Retorna o nome do livro
    
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o autor do livro
   
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Retorna a editora
    
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Retorna a matricula da pessoa
     
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Retorna o ISBN do livro
     
     */
    public String getAno() {
        return ano;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }
    
    public String getDisponivelString(){
        if(disponivel) return "Sim";
        else return "Não";
    }
    
    public String getLabel(){
        if(disponivel)return "label-success";
        else return "label-danger";
    }

    /**
     * Retorna o codigo gerado para o menu
    
     */
    private int generateCodigo() {
        return (CODIGO_GERADO++);
    }

    public int getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(int retiradas) {
        this.retiradas = retiradas;
    }

    @Override
    public String toString() {
        return "Livro{" + "isbn=" + isbn + ", nome=" + nome + ", autor=" + autor + ", editora=" + editora + ", ano=" + ano + '}';
    }


    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}