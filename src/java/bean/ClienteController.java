package bean;

import model.Cliente;
import session.ClienteFacade;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import model.Devolucao;
import session.DevolucaoFacade;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {
    //Crud
    private Cliente clienteSelecionado;
    private String matricula;
    private String nomeBusca;
    private List<Cliente> pesquisaNome;
    private List<Cliente> maisRetiram;
    private List<Cliente> maisAtrasam;
     @Inject
    private ClienteFacade clienteFacade;
     @Inject
    private DevolucaoFacade devolucaoFacade;

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public List<Cliente> getPesquisaNome() {
        return pesquisaNome;
    }

    public void setPesquisaNome(List<Cliente> pesquisaNome) {
        this.pesquisaNome = pesquisaNome;
    }

    public List<Cliente> getMaisRetiram() {
        return maisRetiram;
    }

    public void setMaisRetiram(List<Cliente> maisRetiram) {
        this.maisRetiram = maisRetiram;
    }

    public List<Cliente> getMaisAtrasam() {
        return maisAtrasam;
    }

    public void setMaisAtrasam(List<Cliente> maisAtrasam) {
        this.maisAtrasam = maisAtrasam;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public DevolucaoFacade getDevolucaoFacade() {
        return devolucaoFacade;
    }

    public void setDevolucaoFacade(DevolucaoFacade devolucaoFacade) {
        this.devolucaoFacade = devolucaoFacade;
    }
     
     
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
    
    public String adicionarPesquisa() {
        pesquisaNome = buscarCliente(this.nomeBusca); 
        return ("/admin/buscaCliente?faces-redirect=true");
    }
    
    public String adicionarPesquisaRetirada() {
        pesquisaNome = buscarCliente(this.nomeBusca); 
        return ("/admin/buscaCliente?faces-redirect=true");
    }
    
    public String adicionarPesquisaUsuario() {
        pesquisaNome = buscarCliente(this.nomeBusca); 
        return ("/usuario/buscaCliente?faces-redirect=true");
    }
    /*
    public void removerCliente(Cliente cliente){
        clienteFacade.remover(cliente);
    }  
*/
     public void removerCliente(Cliente cliente){// verifica se este cliente está relacionado a tabela devolução
        List<Devolucao> pesquisaCascade = devolucaoFacade.buscarClienteExclusao(cliente.getMatricula());
        for (Devolucao devolucao : pesquisaCascade) {
            devolucaoFacade.remover(devolucao);      
        }
        clienteFacade.remover(cliente);
    } 
     
     public List<Cliente> buscarCliente(String nome){
        return clienteFacade.buscarPorNome(nome);
    }
    
    public String clientesMaisRetiram(){
        maisRetiram = this.clienteFacade.topQueRetiram();
        return("/admin/clientesMaisRetiram?faces-redirect=true");
    }
    
    public String clientesMaisRetiramUsuario(){
        maisRetiram = this.clienteFacade.topQueRetiram();
        return("/usuario/clientesMaisRetiram?faces-redirect=true");
    }
    
    public String clientesMaisAtrasam(){
        maisAtrasam = this.clienteFacade.topQueAtrasam();
        return("/admin/clientesMaisAtrasam?faces-redirect=true");
    }
    
    public String clientesMaisAtrasamUsuario(){
        maisAtrasam = this.clienteFacade.topQueAtrasam();
        return("/usuario/clientesMaisAtrasam?faces-redirect=true");
    }
    
}
