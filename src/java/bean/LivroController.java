package bean;

import model.Livro;
import session.LivroFacade;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name = "livroController")
@SessionScoped
public class LivroController implements Serializable {
  private Livro livroSelecionado;
    private String id;
    @Inject
    private LivroFacade livroFacade;

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
    
    public void removerLivro(Livro livro){
        livroFacade.remover(livro);
    }    
    
    public Livro buscarLivroPorNome(String nome){
        for(Livro l: getListaLivros())
            if(l.getNome().equals(nome))
                return l;
        return null;
    }
    
   
}
