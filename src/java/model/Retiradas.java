/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import model.Cliente;
import model.Livro;
/**
 *
 * @author mari
 */
@Entity
public class Retiradas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date retirada;    
    private Date devolvido;
    private Date entrega;
    private String cliente;
    private String livro;
    private Boolean livroDevolvido = false;
    
     public Retiradas() {
        
    }

    public Retiradas(String cliente, String livro) {
        this.cliente = cliente;
        this.livro = livro;
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        this.setEntrega(new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)));
    }
 
    public Date getEntrega() {
        return entrega;
    }
 /**
     * Formata data de entrega
     *
     * @return a entrega de um livro com data formatada "dd/MM/yyyy".
     */
    
    public String getEntregaFormatada() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(entrega);
    }
    
     /**
     * Informa Entrega (com base no parâmetro construtor)
     *
     * @param entrega recebida do parâmentro contrutor.
     */
    
    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }
     
      /**
     * Recebe cliente que retira um livro
     *
     * @return cliente que retirou um livro
     */
    public String getCliente() {
        return cliente;
    }
      
   /**
     * Informa cliente que retirou um livro
     *
     * @param cliente que retira um livro.
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
     /**
     * Recebe livro que foi retirado
     *
     * @return livro que foi retirado.
     */
    public String getLivro() {
        return livro;
    }

    /**
     * Informa livro a ser retirado
     *
     * @param livro recebe novo livro retirado.
     */
    public void setLivro(String livro) {
        this.livro = livro;
    }
    
    /**
     * Recebe data de retirada
     *
     * @return retirada, data da retirada.
     */
    public Date getRetirada() {
        return retirada;
    }
    
     /**
     * recebe data de devolução no mesmo livro, caso ele ainda tenha sido
     * devolvido, não poderá ser retirado
     *
     * @return devolvido, data.
     */
    public Date getDevolvido() {
        return devolvido;
    }
    
      /**
     * Informa caso o livro a ser retirado já tenha sido devolvido anteriormente
     *
     * @param devolvido, a data de devolução anterior ao próximo empréstimo.
     */
    public void setDevolvido(Date devolvido) {
        this.devolvido = devolvido;
    }
    
    /**
     * Formata data de devolução anterior ao próximo empréstimo
     *
     * @return devolvido. data formatada "dd/MM/yyyy".
     */
    public String getDevolvidoFormatada() {
        if (devolvido != null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(devolvido);
        } else {
            return "";
        }
    }
     /**
     * Formata data de retirada anterior ao próximo empréstimo
     *
     * @return retirada. data formatada "dd/MM/yyyy".
     */
    public String getRetiradaFormatada() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(retirada);
    }
    /**
     * Recebe data da Retirada atual
     *
     * @param retirada de livro.
     */
    public void setRetirada(Date retirada) {
        this.retirada = retirada;
    } 
     /**
     * Retorna status de devolução do livro a ser emprestado
     *
     * @return livroDevolvido, verdadeiro ou falso.
     */
    public Boolean getLivroDevolvido() {
        return livroDevolvido;
    }
    /**
     * Recebe status de devolução do livro a ser emprestado
     *
     * @param livroDevolvido verdadeiro ou falso.
     */
    public void setLivroDevolvido(Boolean livroDevolvido) {
        this.livroDevolvido = livroDevolvido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
     /**
     * Retorna cód id da retirada
     *
     * @return id, retirada de livro.
     */
    

    @Override
    public String toString() {
        return "model.Retiradas[ id=" + id + " ]";
    }
    
}
