
package bean;


import static Util.DateUtil.dateToString;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Cliente;
import model.Devolucao;
import model.Livro;
import model.Retiradas;
import session.ClienteFacade;
import session.LivroFacade;
import session.DevolucaoFacade;
import session.RetiradasFacade;
/**
 *
 * @author mari
 */
@Named
@ApplicationScoped
public class DevolucaoController implements Serializable {

    @Inject
    DevolucaoFacade devolucaoFacade;
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
    @Inject
    RetiradasController RetiradasController;
    private long id;
    private long matriculaCliente;
    private long idLivro;
    private String dtFormatada;
    long DAY_IN_MS = 1000 * 60 * 60 * 24; // formatar data entrega
    private List<Devolucao> listaDevolucao;
    private Devolucao devolucaoSelecionada;
      private Date dataAtual = new Date(System.currentTimeMillis());

    public DevolucaoController() {
       devolucaoSelecionada = new Devolucao();       
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

    public Devolucao getDevolucaoSelecionada() {
        return devolucaoSelecionada;
    }

    public void setDevolucaoSelecionada(Devolucao devolucaoSelecionada) {
        this.devolucaoSelecionada = devolucaoSelecionada;
    }

    public List<Devolucao> getListaDevolucao() {
        return devolucaoFacade.listar();
    }
    
    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }
    
    public void devolverLivro(Retiradas retirada){
        Livro l = retirada.getLivro();
        Cliente c = retirada.getCliente();
        l.setDisponivel(true);
        l.setDataLiberacao(RetiradasController.getDataLiberacao());
        livroFacade.salvar(l);
        atrasoCliente(c, retirada);
        adicionarDevolucao(retirada);
        retiradaFacade.remover(retirada);        
    }
    
    public void atrasoCliente(Cliente c, Retiradas r){
        if (r.getDataDevolucao().before(getDataAtual()))
            c.setAtrasos(c.getAtrasos() + 1);
        
        clienteFacade.salvar(c);   
    }

    public String novaDevolucao() {
        devolucaoSelecionada = new Devolucao();
        return ("/admin/devolucao?faces-redirect=true");
    }

    public String novaDevolucaoUsuario() {
        devolucaoSelecionada = new Devolucao();
        return ("/usuario/devolucao?faces-redirect=true");
    }

    public String adicionarDevolucao(Retiradas retirada) {
            devolucaoSelecionada.setCliente(retirada.getCliente());
            devolucaoSelecionada.setLivro(retirada.getLivro());
            devolucaoSelecionada.setDataRetirada(retirada.getDataRetirada());
            devolucaoSelecionada.setDataDevolucao(retirada.getDataDevolucao());
            devolucaoSelecionada.setDataDevolvido(dataAtual);
            devolucaoFacade.salvar(devolucaoSelecionada);            
            return (this.novaDevolucao());
        }

   public String formataData(Date data) {
        this.dtFormatada = dateToString(data);
        return this.dtFormatada;
    }

    public String mostrarDevolucao(){        
        return("/admin/listaDevolucao?faces-redirect=true");
    }
    
    public String mostrarDevolucaoUsuario(){        
        return("/usuario/listaDevolucao?faces-redirect=true");
    }

    public void removerDevolucao(Devolucao devolucao) {
        devolucaoFacade.remover(devolucao);
    }
    
    public String getAtrasadoString(Devolucao d){
        if(d.getDataDevolucao().before(d.getDataDevolvido())) return "Sim";
        else return "Não";
    }
    
    public String getLabel(Devolucao d){
        if(d.getDataDevolucao().before(d.getDataDevolvido())) return "label-danger";
        else return "label-success";
    }    
}