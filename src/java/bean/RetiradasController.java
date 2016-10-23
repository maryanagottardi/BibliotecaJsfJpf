package bean;

import model.Retiradas;
import session.RetiradasFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name = "retiradasController")
@SessionScoped
public class RetiradasController implements Serializable {

   //CRUD
    private List<Retiradas> listaRetiradas;
    private Retiradas retiradaSelecionada;
    @Inject
    private RetiradasFacade retiradaFacade;
    /*
    private DataModel items = null;
    @EJB
    private session.RetiradasFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
*/
    public RetiradasController() {
        retiradaSelecionada = new Retiradas();
        listaRetiradas = new ArrayList<Retiradas>();
        listaRetiradas.add(new Retiradas("Beltrano", "Harry Potter I"));
          
    }
 public Retiradas getRetiradaSelecionada() {
        return retiradaSelecionada;
    }
    
      public void setRetiradaSelecionada(Retiradas retiradaSelecionada) {
        this.retiradaSelecionada = retiradaSelecionada;
    }
      
      public List<Retiradas> getListaRetiradas(){
        return retiradaFacade.listar();
    }

      public String novaRetirada(){
        retiradaSelecionada = new Retiradas();
         return("/admin/retirada?faces-redirect=true");
    }
        public String novaRetiradaUsuario(){
        retiradaSelecionada=new Retiradas();
        return("/usuario/retirada?faces-redirect=true");
    }
      
      public String adicionarRetirada(){
        retiradaFacade.salvar(retiradaSelecionada);
        return(this.novaRetirada());
    }

     public String editarRetirada(Retiradas r){
        retiradaSelecionada = r;
         return("/admin/edicaoUsuarios?faces-redirect=true");       
    }
     
      public void removerRetirada(Retiradas retirada){
        retiradaFacade.remover(retirada);
    }
   
    
    public String atualizarRetirada(){
        retiradaFacade.salvar(retiradaSelecionada);
         return("/admin/index?faces-redirect=true");
    }
   
}
