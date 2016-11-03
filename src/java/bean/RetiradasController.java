package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Cliente;
import model.Livro;
import model.Retiradas;
import session.ClienteFacade;
import session.LivroFacade;
import session.RetiradasFacade;
import static Util.DateUtil.dateToString;

@Named
@ApplicationScoped
public class RetiradasController implements Serializable {

   //CRUD
    @Inject
    RetiradasFacade retiradaFacade;
    @Inject
    LivroFacade livroFacade;
    @Inject
    ClienteFacade clienteFacade;
   // @Inject
   // ClienteController clienteController;
   // @Inject
   // LivroController livroController;
    private long id;
    private long matriculaCliente;
    private long idLivro;
    private String dtFormatada;
    private Livro livroSelecionado;

    private List<Retiradas> listaRetiradas;
    private Retiradas retiradaSelecionada;
    
     public RetiradasController() {
        retiradaSelecionada = new Retiradas();
        livroSelecionado = new Livro();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMatriculaCliente() {
        return matriculaCliente;
    }

    public void setMatriculaCliente(long matriculaCliente) {
        this.matriculaCliente = matriculaCliente;
    }

    public long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(long idLivro) {
        this.idLivro = idLivro;
    }

    public String getDtFormatada() {
        return dtFormatada;
    }

    public void setDtFormatada(String dtFormatada) {
        this.dtFormatada = dtFormatada;
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }
    
    public Retiradas getRetiradaSelecionada() {
        return retiradaSelecionada;
    }

    public void setRetiradaSelecionada(Retiradas retiradaSelecionada) {
        this.retiradaSelecionada = retiradaSelecionada;
    }

    public List<Retiradas> getListaRetiradas() {
        return retiradaFacade.listar();
    }

    public String novaRetirada() {
        retiradaSelecionada = new Retiradas();
        return ("/admin/retirada?faces-redirect=true");
    }

    public String novaRetiradaUsuario() {
        retiradaSelecionada = new Retiradas();
        return ("/usuario/retirada?faces-redirect=true");
    }

    public String adicionarRetirada() {
        Livro l = this.livroSelecionado;
        Cliente c = buscaClienteMatricula(this.getMatriculaCliente());
        l.setDisponivel(false);
        l.setRetiradas(l.getRetiradas()+1);
        retiradaSelecionada.setCliente(c);
        retiradaSelecionada.setLivro(l);
        retiradaSelecionada.setDataRetirada(new Date(System.currentTimeMillis()));
        retiradaSelecionada.setDataDevolucao(new Date(System.currentTimeMillis() + (7 * (1000 * 60 * 60 * 24))));
        livroFacade.salvar(l);
        retiradaFacade.salvar(retiradaSelecionada);
        return (this.novaRetirada());
    }    
   
    public Cliente buscaClienteMatricula(Long matricula){
        return clienteFacade.buscar(matricula);
    }
   
    public Livro buscaLivroID(Long id){
        return livroFacade.buscar(id);
    }
    
    public List<Livro> getLivrosDisponiveis(){
        List<Livro> disponiveis = new ArrayList<>();
        for(Livro l : this.livroFacade.listar()){
            if(l.isDisponivel()){
                disponiveis.add(l);
            }
        }
        return disponiveis;
    }
    
    public String formataData(Date data){
        this.dtFormatada = dateToString(data);
        return this.dtFormatada;
    }

    public String editarRetirada(Retiradas u) {
        retiradaSelecionada = u;
        return ("/admin/edicaoUsuarios?faces-redirect=true"); 
    }

    public String atualizarRetirada() {
        retiradaFacade.salvar(retiradaSelecionada);
        return ("/admin/index?faces-redirect=true"); 
    }

    public void removerRetirada(Retiradas retirada) {
        retiradaFacade.remover(retirada);
    }
    
    public Livro buscarLivroPorNome(String nome){
        for(Livro l: getLivrosDisponiveis())
            if(l.getNome().equals(nome))
                return l;
        return null;
    }

}
