package bean;

import model.Cliente;
import session.ClienteFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {
    //Crud
    private List<Cliente> listaClientes;
    private Cliente clienteSelecionado;
    private String matricula;
     @Inject
    private ClienteFacade clienteFacade;
     
      public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public ClienteController() {
         clienteSelecionado = new Cliente();
         
        listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente("01", "Fulano", "3333-4544"));
        listaClientes.add(new Cliente("02", "Beltrano", "3444-4545"));
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
  
  /*
   public List<Cliente> getListaClientes() {
        return listaClientes;
    }
 */
 public String novoCliente(){
        clienteSelecionado = new Cliente();
        return("/admin/cadastroClientes?faces-redirect=true");
    }
  public String adicionarCliente(){
        clienteFacade.salvar(clienteSelecionado);
   return("/admin/confirmaCadastroCliente?faces-redirect=true");       
// return(this.novoCliente());
    }
    public String editarCliente(Cliente l){
        clienteSelecionado = l;
       return("/admin/edicaoClientes?faces-redirect=true");      
    }
     
    public void removerCliente(Cliente cliente){
        clienteFacade.remover(cliente);
    }
   
    
    public String atualizarCliente(){
        clienteFacade.salvar(clienteSelecionado);
         return("/admin/listaClientes?faces-redirect=true");
    }
    
     public Cliente buscarCliente(String matricula) {
        
        for (Cliente cliente : listaClientes) {
            if (cliente.getMatricula().equals(matricula)) {
                return cliente;
            }
        }        
        return null;
    }
}
