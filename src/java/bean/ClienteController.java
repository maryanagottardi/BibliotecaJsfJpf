package bean;

import model.Cliente;
import bean.util.JsfUtil;
import bean.util.PaginationHelper;
import session.ClienteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    private Cliente clienteSelecionado;
     @Inject
    private ClienteFacade clienteFacade;

    public ClienteController() {
         clienteSelecionado = new Cliente();
    }

   public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
  public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }
 public List<Cliente> getListaCliente(){
        return clienteFacade.listar();
    }
 public String novoCliente(){
        clienteSelecionado = new Cliente();
        return("/cadastroClientes");//conferir isto com o professor!
    }
  public String adicionarCliente(){
        clienteFacade.salvar(clienteSelecionado);
        return(this.novoCliente());
    }
    public String editarCliente(Cliente l){
        clienteSelecionado = l;
        return("/cadastroClientes");        
    }
     
    public void removerCliente(Cliente cliente){
        clienteFacade.remover(cliente);
    }
   
    
    public String atualizarCliente(){
        clienteFacade.salvar(clienteSelecionado);
        return("/indexCliente");//ver com o professor
    }
}
