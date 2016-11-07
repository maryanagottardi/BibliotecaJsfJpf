package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    @Inject
    RetiradasFacade retiradaFacade;
    @Inject
    LivroFacade livroFacade;
    @Inject
    ClienteFacade clienteFacade;
    @Inject
    ClienteController clienteController;
    @Inject
    LivroController livroController;
    private long id;
    private long matriculaCliente;
    private long idLivro; 
    private String dtFormatada;
    private Livro livroSelecionado;
    long DAY_IN_MS = 1000 * 60 * 60 * 24;
    Date periodoEmprestimo = new Date(System.currentTimeMillis() + 7 * DAY_IN_MS); // uma semana
    private Date dataLiberacao = new Date(System.currentTimeMillis() + 8 * DAY_IN_MS); //prazo que o livro ficará disponível novamente
    Date dataAtual = new Date(System.currentTimeMillis());

    //CRUD
    private List<Retiradas> listaRetiradas;
    private List<Retiradas> pesquisa;
    private Retiradas retiradaSelecionada;
    private Retiradas pesquisaSelecionada;

    public RetiradasController() {
        pesquisa = new ArrayList<>();
        pesquisaSelecionada = new Retiradas();
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

    public List<Retiradas> getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(List<Retiradas> pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Retiradas getPesquisaSelecionada() {
        return pesquisaSelecionada;
    }

    public void setPesquisaSelecionada(Retiradas pesquisaSelecionada) {
        this.pesquisaSelecionada = pesquisaSelecionada;
    }    

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    public String novaRetirada() {
        retiradaSelecionada = new Retiradas();
        return ("/admin/retirada?faces-redirect=true");
    }

    public String novaRetiradaUsuario() {
        retiradaSelecionada = new Retiradas();
        return ("/usuario/retirada?faces-redirect=true");
    }
    
    public String adicionarPesquisa() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        Livro l = this.livroSelecionado;
        Cliente c = buscaClienteMat(this.getMatriculaCliente());
        if (c != null) {
            pesquisaSelecionada.setCliente(c);
            pesquisaSelecionada.setLivro(l);
            pesquisaSelecionada.setDataRetirada(dataAtual);
            pesquisaSelecionada.setDataDevolucao(periodoEmprestimo);
            pesquisa.add(pesquisaSelecionada);
            return (this.novaRetirada());
        }else{
            FacesMessage mensagemRet = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro!", "Cliente não encontrado!");
            contexto.addMessage("idMensagem", mensagemRet);
        }
        return ("/admin/validacaoCiente?faces-redirect=true");        
    }
    
    public String adicionarPesquisaUsuario() {
        Livro l = this.livroSelecionado;
        Cliente c = buscaClienteMat(this.getMatriculaCliente());
        pesquisaSelecionada.setCliente(c);
        pesquisaSelecionada.setLivro(l);
        pesquisaSelecionada.setDataRetirada(dataAtual);
        pesquisaSelecionada.setDataDevolucao(periodoEmprestimo);
        pesquisa.add(pesquisaSelecionada);
        return (this.novaRetiradaUsuario());
    }

    public void limparPesquisa(Retiradas r) {
        pesquisa.remove(r);
    }

    public String adicionarRetirada() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        //RetiradasMB retiradasMB = (RetiradasMB) contexto.getExternalContext().getApplicationMap().get("RetiradasMB");
        if (!pesquisa.isEmpty()) {
            Livro l = this.livroSelecionado;
            Cliente c = buscaClienteMat(this.getMatriculaCliente());
            l.setDisponivel(false);
            l.setRetiradas(l.getRetiradas() + 1);
            l.setDataLiberacao(dataLiberacao);
            c.setRetiradas(c.getRetiradas() + 1);
            retiradaSelecionada.setCliente(c);
            retiradaSelecionada.setLivro(l);
            retiradaSelecionada.setDataRetirada(dataAtual);
            retiradaSelecionada.setDataDevolucao(periodoEmprestimo);
            livroFacade.salvar(l);
            clienteFacade.salvar(c);
            retiradaFacade.salvar(retiradaSelecionada);
            limparPesquisa(pesquisaSelecionada);
            this.novaRetirada();
            return ("/admin/confirmaRetirada?faces-redirect=true");
        }
        FacesMessage mensagemRet = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro!", "É necessario pesquisar antes!");
        contexto.addMessage("idMensagem", mensagemRet);
        return ("/admin/retirada?faces-redirect=true");
    }
    
    public String adicionarRetiradaUsuario() {
        FacesContext contextoRet = FacesContext.getCurrentInstance();
        //RetiradasMB retiradasMB = (RetiradasMB) contextoRet.getExternalContext().getApplicationMap().get("RetiradasMB");
        if (!pesquisa.isEmpty()) {
            Livro l = this.livroSelecionado;
            Cliente c = buscaClienteMat(this.getMatriculaCliente());
            l.setDisponivel(false);
            l.setRetiradas(l.getRetiradas() + 1);
            l.setDataLiberacao(dataLiberacao);
            c.setRetiradas(c.getRetiradas() + 1);
            retiradaSelecionada.setCliente(c);
            retiradaSelecionada.setLivro(l);
            retiradaSelecionada.setDataRetirada(dataAtual);
            retiradaSelecionada.setDataDevolucao(periodoEmprestimo);
            livroFacade.salvar(l);
            clienteFacade.salvar(c);
            retiradaFacade.salvar(retiradaSelecionada);
            limparPesquisa(pesquisaSelecionada);
            this.novaRetirada();
            return ("/usuario/confirmaRetirada?faces-redirect=true");
        }
        FacesMessage mensagemRet = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro!", "É necessario pesquisar antes!");
        contextoRet.addMessage("idMensagemRet", mensagemRet);
        return ("/usuario/retirada?faces-redirect=true");
    }

    public Cliente buscaClienteMat(Long mat) {
        return clienteFacade.buscar(mat);
    }

    public Livro buscaLivroID(Long id) {
        return livroFacade.buscar(id);
    }

    public List<Livro> getLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro l : this.livroController.getListaLivros()) {
            if (l.isDisponivel()) {
                disponiveis.add(l);
            }
        }
        return disponiveis;
    }

    public String formataData(Date data) {
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
    
    public String mostrarRetiradas(){        
        return("/admin/listaRetiradas?faces-redirect=true");
    }
    
    public String mostrarRetiradasUsuario(){        
        return("/usuario/listaRetiradas?faces-redirect=true");
    }

    public Livro buscarLivroPorNome(String nome) {
        for (Livro l : getLivrosDisponiveis()) {
            if (l.getNome().equals(nome)) {
                return l;
            }
        }
        return null;
    }
    
    public String getAtrasadoString(Retiradas r){
        if(r.getDataDevolucao().before(dataAtual)) return "Sim";
        else return "Não";
    }
    
    public String getLabel(Retiradas r){
        if(r.getDataDevolucao().before(dataAtual)) return "label-danger";
        else return "label-success";
    }
    
}