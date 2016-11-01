package bean;

import model.Retiradas;
import session.RetiradasFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import model.Livro;

@ManagedBean(name = "retiradasController")
@SessionScoped
public class RetiradasController implements Serializable {

   //CRUD
    private List<Retiradas> listaRetiradas;
    private Retiradas retiradaSelecionada;
    @Inject
    private String matricula, cliente;
    private Livro clienteSelecionado;
    ClienteController clienteController;
    private RetiradasFacade retiradaFacade;
    /*
    private DataModel items = null;
    @EJB
    private session.RetiradasFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
*/
    public RetiradasController() {
        retiradaSelecionada = new Retiradas();
        listaRetiradas = new ArrayList<Retiradas>();
        listaRetiradas.add(new Retiradas("Beltrano", "Harry Potter I"));
          
    }
 public Retiradas getRetiradaSelecionada() {
        return retiradaSelecionada;
    }

    public void setRetiradaSelecionada(Retiradas retiradaSelecionada) {
        this.retiradaSelecionada = retiradaSelecionada;
    }

    public List<Retiradas> getListaRetiradas() {
        return listaRetiradas;
    }

    public void setListaRetiradas(List<Retiradas> listaRetiradas) {
        this.listaRetiradas = listaRetiradas;
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
        listaRetiradas.add(retiradaSelecionada);
        return (this.novaRetirada());
    }

    public String editarRetirada(Retiradas u) {
        retiradaSelecionada = u;
        return ("/admin/edicaoUsuarios?faces-redirect=true");
    }

    public String atualizarRetirada() {
        return ("/admin/index?faces-redirect=true");
    }

    public void removerRetirada(Retiradas retirada) {
        listaRetiradas.remove(retirada);
    }
}
