package bean;

import model.Usuario;
import session.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;


@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSelecionado;
    @Inject
    private UsuarioFacade usuarioFacade;
   // private DataModel items = null;
  /* @EJB
    private session.UsuarioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
*/
    public UsuarioController() {
        usuarioSelecionado = new Usuario();
        
    }

   public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }
   
    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    
    public List<Usuario> getListaUsuarios(){
        return usuarioFacade.listar();
    }
    
     public String novoUsuario(){
        usuarioSelecionado = new Usuario();
        return("/admin/cadastroUsuarios?faces-redirect=true");
    }
    
    public String adicionarUsuario(){
        usuarioFacade.salvar(usuarioSelecionado);
        this.novoUsuario();
        return("/admin/confirmaCadastroUsuario?faces-redirect=true");
    }
    
    public String mostrarUsuarios(){        
        return("/admin/listaUsuarios?faces-redirect=true");
    }
    
    public String editarUsuario(Usuario u){
        usuarioSelecionado = u;
        return("/admin/edicaoUsuarios?faces-redirect=true");        
    }
    
    public String editarPerfil(Usuario u){
        usuarioSelecionado = u;
        return("/usuario/editarPerfil?faces-redirect=true");
    }
    
    public String atualizarUsuario(){
        usuarioFacade.salvar(usuarioSelecionado);
        return("/admin/listaUsuarios?faces-redirect=true");
    }
    
    public void removerUsuario(Usuario usuario){
        usuarioFacade.remover(usuario);
    }
    
}
