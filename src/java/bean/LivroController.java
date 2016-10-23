package bean;

import model.Livro;
import session.LivroFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name = "livroController")
@SessionScoped
public class LivroController implements Serializable {
    //crud
    private List<Livro> listaLivros;
    private Livro livroSelecionado;
    @Inject
    private LivroFacade livroFacade;

    public LivroController() {
        livroSelecionado = new Livro();
        listaLivros = new ArrayList<Livro>();
        listaLivros.add(new Livro("1", "Harry Potter I", "JK", "asd", "1995"));
        listaLivros.add(new Livro("2", "Harry Potter II", "JK", "asd", "1999"));
    }
 public Livro getLivroSelecionado() {
        return livroSelecionado;
    }
  public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }
 public List<Livro> getListaLivro(){
        return livroFacade.listar();
    }
 public String novoLivro(){
        livroSelecionado = new Livro();
        return("/admin/cadastroLivros?faces-redirect=true");
    }
  public String adicionarLivro(){
        livroFacade.salvar(livroSelecionado);
        return("/admin/confirmaCadastroLivro?faces-redirect=true");
       // return(this.novoLivro());
    }
   public String mostrarLivros(){        
        return("/admin/listaLivros?faces-redirect=true");
    }
    
    public String mostrarLivrosUsuario(){        
        return("/usuario/listaLivros?faces-redirect=true");
    }
   public String editarLivro(Livro l){
        livroSelecionado = l;
        return("/admin/edicaoLivros?faces-redirect=true");        
    }
     
      public void removerLivro(Livro livro){
        livroFacade.remover(livro);
    }
   
    
    public String atualizarLivro(){
        livroFacade.salvar(livroSelecionado);
          return("/admin/listaLivros?faces-redirect=true");
    }
  
  
}
