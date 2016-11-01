package bean;

import model.Cliente;
import session.ClienteFacade;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {
    //Crud
    private Cliente clienteSelecionado;
    private String matricula;
     @Inject
    private ClienteFacade clienteFacade;
     
      public ClienteController() {
         clienteSelecionado = new Cliente();
      }
     
      public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente usuarioSelecionado) {
        this.clienteSelecionado = usuarioSelecionado;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public List<Cliente> getListaClientes(){
        return clienteFacade.listar();
    }
    
    public String novoCliente(){
        clienteSelecionado = new Cliente();
        return("/admin/cadastroClientes?faces-redirect=true");
    }
    
    public String adicionarCliente(){
        clienteFacade.salvar(clienteSelecionado);
        this.novoCliente();
        return("/admin/confirmaCadastroCliente?faces-redirect=true");
    }
    
    public String mostrarClientes(){        
        return("/admin/listaClientes?faces-redirect=true");
    }
    
    public String mostrarClientesUsuario(){        
        return("/usuario/listaClientes?faces-redirect=true");
    }
    
    public String editarCliente(Cliente c){
        clienteSelecionado = c;
        return("/admin/edicaoClientes?faces-redirect=true");        
    }
    
    public String atualizarCliente(){
        clienteFacade.salvar(clienteSelecionado);
        return("/admin/listaClientes?faces-redirect=true");
    }
    
    public void removerCliente(Cliente cliente){
        clienteFacade.remover(cliente);
    }    
}
