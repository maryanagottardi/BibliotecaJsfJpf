
package session;

import java.util.List;
import model.Livro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mari
 */
@Stateless
public class LivroFacade extends AbstractFacade<Livro> {
    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager manager;

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }

    public LivroFacade() {
        super(Livro.class);
    }
     public void salvar(Livro l){
        //validar parâmetros
        if(l.getId()==null){
            super.adicionar(l);
        }
        else
            super.atualizar(l);
    }
      public List<Livro> buscarPorTitulo(String titulo) {
        Query query = manager.createQuery("SELECT p FROM Livro p WHERE  LOWER(p.nome) LIKE :nome");
        query.setParameter("nome", "%"+titulo.toLowerCase()+"%");
        return query.getResultList();
    }
    
    public List<Livro> maisRetirados() {
        String jpql = "SELECT p FROM Livro p WHERE p.retiradas > 2 "
                + "ORDER BY p.retiradas DESC";   
        Query query = manager.createQuery(jpql);
        query.setMaxResults(4);
        return query.getResultList();
    }
    
    public List<Livro> disponiveis() {
        Query query = manager.createQuery("SELECT p FROM Livro p WHERE p.disponivel = TRUE ORDER BY p.nome");
        return query.getResultList();
    }
}
