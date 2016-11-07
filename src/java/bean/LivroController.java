package bean;

import model.Livro;
import session.LivroFacade;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import model.Devolucao;
import session.DevolucaoFacade;

@ManagedBean(name = "livroController")
@SessionScoped
public class LivroController implements Serializable {
  private Livro livroSelecionado;
    private String id;
    private List<Livro> pesquisaTitulo;
    private List<Livro> maisRetirados;
    private List<Livro> disponiveis;
    private String tituloBusca;
    @Inject
    private LivroFacade livroFacade;
     @Inject
    private DevolucaoFacade devolucaoFacade;

    public List<Livro> getPesquisaTitulo() {
        return pesquisaTitulo;
    }

    public void setPesquisaTitulo(List<Livro> pesquisaTitulo) {
        this.pesquisaTitulo = pesquisaTitulo;
    }

    public List<Livro> getMaisRetirados() {
        return maisRetirados;
    }

    public void setMaisRetirados(List<Livro> maisRetirados) {
        this.maisRetirados = maisRetirados;
    }

    public List<Livro> getDisponiveis() {
        return disponiveis;
    }

    public void setDisponiveis(List<Livro> disponiveis) {
        this.disponiveis = disponiveis;
    }

    public String getTituloBusca() {
        return tituloBusca;
    }

    public void setTituloBusca(String tituloBusca) {
        this.tituloBusca = tituloBusca;
    }

    public LivroFacade getLivroFacade() {
        return livroFacade;
    }

    public void setLivroFacade(LivroFacade livroFacade) {
        this.livroFacade = livroFacade;
    }

    public DevolucaoFacade getDevolucaoFacade() {
        return devolucaoFacade;
    }

    public void setDevolucaoFacade(DevolucaoFacade devolucaoFacade) {
        this.devolucaoFacade = devolucaoFacade;
    }
     
     

    public LivroController() {
        livroSelecionado = new Livro();
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro usuarioSelecionado) {
        this.livroSelecionado = usuarioSelecionado;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public List<Livro> getListaLivros(){
        return livroFacade.listar();
    }
    
    public String novoLivro(){
        livroSelecionado = new Livro();
        return("/admin/cadastroLivros?faces-redirect=true");
    }
    
    public String adicionarLivro(){
        livroFacade.salvar(livroSelecionado);
        this.novoLivro();
        return("/admin/confirmaCadastroLivro?faces-redirect=true");
    }
    
    public String mostrarLivros(){        
        return("/admin/listaLivros?faces-redirect=true");
    }
    
    public String mostrarLivrosUsuario(){        
        return("/usuario/listaLivros?faces-redirect=true");
    }
    
    public String editarLivro(Livro c){
        livroSelecionado = c;
        return("/admin/edicaoLivros?faces-redirect=true");        
    }
    
    public String atualizarLivro(){
        livroFacade.salvar(livroSelecionado);
        return("/admin/listaLivros?faces-redirect=true");
    }
    /*
    public void removerLivro(Livro livro){
        livroFacade.remover(livro);
    }    
    */
    public void removerLivro(Livro livro){// verifica se este livro está relacionado a tabela devolução
        List<Devolucao> pesquisaCascade = devolucaoFacade.buscarLivroExclusao(livro.getId());
        for (Devolucao devolucao : pesquisaCascade) {
            devolucaoFacade.remover(devolucao);      
        }
        livroFacade.remover(livro);
    } 
    public Livro buscarLivroPorNome(String nome){
        for(Livro l: getListaLivros())
            if(l.getNome().equals(nome))
                return l;
        return null;
    }
    
    public String adicionarPesquisa() {
        pesquisaTitulo = buscarLivroTitulo(this.tituloBusca); 
        return ("/admin/buscaLivros?faces-redirect=true");
    }
    
    public String adicionarPesquisaUsuario() {
        pesquisaTitulo = buscarLivroTitulo(this.tituloBusca); 
        return ("/usuario/buscaLivros?faces-redirect=true");
    }
    
    public List<Livro> buscarLivroTitulo(String titulo){
        return livroFacade.buscarPorTitulo(titulo);
    }
    
    public String livrosMaisRetirados(){
        maisRetirados = this.livroFacade.maisRetirados();
        return("/admin/livrosMaisRetirados?faces-redirect=true");
    }
    
    public String livrosMaisRetiradosUsuario(){
        maisRetirados = this.livroFacade.maisRetirados();
        return("/usuario/livrosMaisRetirados?faces-redirect=true");
    }
    
    public String livrosDisponiveis(){
        disponiveis = this.livroFacade.disponiveis();
        return("/admin/livrosDisponiveis?faces-redirect=true");
    }
    
    public String livrosDisponiveisUsuario(){
        disponiveis = this.livroFacade.disponiveis();
        return("/usuario/livrosDisponiveis?faces-redirect=true");
    }
        
}
