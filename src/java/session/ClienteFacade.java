/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Cliente;

/**
 *
 * @author mari
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager manager;

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
      public void salvar(Cliente u){
        //validar parãmetros
        if(u.getMatricula()==null){
            super.adicionar(u);
        }
        else
            super.atualizar(u);
    }
}
