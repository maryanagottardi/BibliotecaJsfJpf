
package model;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author mari
 */
@Entity
public class Retiradas implements Serializable {
    private static final long serialVersionUID = 1L;   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Livro livro;
    @OneToOne
    private Cliente cliente;  
    @Temporal (value=TemporalType.DATE)
    private Date dataRetirada;
    @Temporal (value=TemporalType.DATE)
    private Date dataDevolucao;
//    long DAY_IN_MS = 1000 * 60 * 60 * 24; // formatar data entrega

     public Retiradas() {
        
    }

    public Retiradas(Livro livro, Cliente cliente, Date dataRetirada, Date dataDevolucao) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataRetirada = new Date(System.currentTimeMillis());
        this.dataDevolucao = new Date(System.currentTimeMillis() + (7 * (1000 * 60 * 60 * 24)));
    }
 
   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retiradas)) {
            return false;
        }
        Retiradas other = (Retiradas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Retiradas[ id=" + id + " ]";
    }
    
}
