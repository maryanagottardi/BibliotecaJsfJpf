
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Retiradas;

/**
 *
 * @author mari
 */
@Stateless
public class RetiradasFacade extends AbstractFacade<Retiradas> {
    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager manager;

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }

    public RetiradasFacade() {
        super(Retiradas.class);
    }
     public void salvar(Retiradas l){
        //validar parÃ¢metros
        if(l.getId()==null){
            super.adicionar(l);
        }
        else
            super.atualizar(l);
    }
}
