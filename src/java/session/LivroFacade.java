
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Livro;

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
}
