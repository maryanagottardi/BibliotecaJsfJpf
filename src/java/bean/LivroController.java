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
    @Inject
    private LivroFacade livroFacade;

    public LivroController() {
         livroSelecionado = new Livro();
    }
 public Livro getUsuarioSelecionado() {
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
        return("/formularioCadastroLivro");
    }
  public String adicionarLivro(){
        livroFacade.salvar(livroSelecionado);
        return(this.novoLivro());
    }
   public String editarLivro(Livro l){
        livroSelecionado = l;
        return("/formularioEditarLivro");        
    }
     
      public void removerLivro(Livro livro){
        livroFacade.remover(livro);
    }
   
    
    public String atualizarLivro(){
        livroFacade.salvar(livroSelecionado);
        return("/indexLivro");
    }
  
  
}
