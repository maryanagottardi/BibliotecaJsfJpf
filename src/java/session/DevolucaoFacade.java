
package session;
import java.util.List;
import model.Devolucao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mari
 */
@Stateless
public class DevolucaoFacade extends AbstractFacade<Devolucao>{
    @PersistenceContext(unitName="BibliotecaPU")
    private EntityManager manager;
    
    public DevolucaoFacade(){
        super(Devolucao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }
    
    public void salvar(Devolucao l){
        //validar parametros
        if(l.getId()==null){
            super.adicionar(l);
        }
        else
            super.atualizar(l);
    }
    
    //metodo usado para deletar livro em cascata = (cascade = {CascadeType.PERSIST}) não funciona
    public List<Devolucao> buscarLivroExclusao(Long id) {
        Query query = manager.createQuery("SELECT p FROM Devolucao p WHERE p.livro.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    //metodo usado para deletar cliente em cascata = (cascade = {CascadeType.PERSIST}) não funciona
    public List<Devolucao> buscarClienteExclusao(Long matricula) {
        Query query = manager.createQuery("SELECT p FROM Devolucao p WHERE p.cliente.matricula = :matricula");
        query.setParameter("matricula", matricula);
        return query.getResultList();
    }
}
