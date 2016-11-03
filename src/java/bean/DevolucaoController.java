
package bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import model.Retiradas;
import session.RetiradasFacade;

/**
 *
 * @author mari
 */
@ManagedBean(name = "devolucaoController")
@SessionScoped
public class DevolucaoController implements Serializable {
    //Crud
    private List<Retiradas> listaRetiradas;
    private Retiradas retiradaSelecionada;
     @Inject
    private RetiradasFacade retiradasFacade;
     
     

    public DevolucaoController() {
        retiradaSelecionada = new Retiradas();
        listaRetiradas = new ArrayList<Retiradas>();
       // listaRetiradas.add(new Retiradas("Beltrano", "Harry Potter I"));
        
    }

    public List<Retiradas> getListaRetiradas() {
        return listaRetiradas;
    }

    public void setListaRetiradas(List<Retiradas> listaRetiradas) {
        this.listaRetiradas = listaRetiradas;
    }

    public Retiradas getRetiradaSelecionada() {
        return retiradaSelecionada;
    }

    public void setRetiradaSelecionada(Retiradas retiradaSelecionada) {
        this.retiradaSelecionada = retiradaSelecionada;
    }

    public RetiradasFacade getRetiradasFacade() {
        return retiradasFacade;
    }

    public void setRetiradasFacade(RetiradasFacade retiradasFacade) {
        this.retiradasFacade = retiradasFacade;
    }
    

   public String novaDevolucao(){
        retiradaSelecionada=new Retiradas();
        return("/admin/devolucao?faces-redirect=true");
    }
    
    public String novaDevolucaoUsuario(){
        retiradaSelecionada=new Retiradas();
        return("/usuario/devolucao?faces-redirect=true");
    }
 
}
