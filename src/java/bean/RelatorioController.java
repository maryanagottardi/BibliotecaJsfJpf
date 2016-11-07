
package bean;



import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped //Application, pois os usuários cadastrados deverão permanecer mesmo se fizer logout.
public class RelatorioController {

    @Inject
    LoginController loginController;

    public RelatorioController() {

    }

    public String mostrarRelatorios() {
        if (loginController!=null && loginController.estaLogado() && loginController.eAdmin())
            return ("/admin/relatorios?faces-redirect=true");
        return ("/usuario/relatorios?faces-redirect=true");
    }
}
