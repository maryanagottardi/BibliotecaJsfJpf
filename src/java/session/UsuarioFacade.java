
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Usuario;

/**
 *
 * @author mari
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager manager;

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
      public void salvar(Usuario u){
        //validar parâmetros
        if(u.getId()==null){
            super.adicionar(u);
        }
        else
            super.atualizar(u);
    }
}


