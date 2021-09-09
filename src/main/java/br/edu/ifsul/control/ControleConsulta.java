/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.control;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.model.Consulta;
import br.edu.ifsul.model.Exame;
import br.edu.ifsul.model.Medico;
import br.edu.ifsul.model.Receituario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author matheus
 */
@Named(value = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable {

    @EJB
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    @EJB
    private MedicoDAO<Medico> daoMedico;
    @EJB
    private PacienteDAO<PacienteDAO> daoPaciente;
    @EJB
    private MedicamentoDAO<MedicamentoDAO> daoMedicamento;

    private Receituario receituario;
    private Boolean novoReceituario;

    private Exame exame;
    private Boolean novoExame;

    public ControleConsulta() {

    }

    public void novoExame() {
        novoExame = true;
        exame = new Exame();
    }

    public void alterarExame(int index) {
        exame = objeto.getExames().get(index);
        novoExame = false;
    }

    public void salvarExame() {
        if (novoExame) {
            objeto.adicionarExame(exame);
        }
        Util.mensagemInformacao("Exame adicionado ou atualizado com sucesso");
    }

    public void removerExame(int index) {
        objeto.removerExame(index);
        Util.mensagemInformacao("Exame removido com sucesso!");
    }

    public void novoReceituario() {
        novoReceituario = true;
        receituario = new Receituario();
    }

    public void alterarReceituario(int index) {
        receituario = objeto.getReceituarios().get(index);
        novoReceituario = false;
    }

    public void salvarReceituario() {
        if (novoReceituario) {
            objeto.adicionarReceituario(receituario);
        }
        Util.mensagemInformacao("Receituário adicionado ou atualizado com sucesso");
    }

    public void removerReceituario(int index) {
        objeto.removerReceituario(index);
        Util.mensagemInformacao("Receituário removido com sucesso!");
    }

    public String listar() {
        return "/privado/consulta/listar?faces-redirect=true";
    }

    public void novo() {
        setObjeto(new Consulta());
    }

    public void alterar(Object id) {
        try {
            setObjeto(getDao().localizar(id));
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            setObjeto(getDao().localizar(id));
            getDao().remover(getObjeto());
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (getObjeto().getId() == null) {
                getDao().persist(getObjeto());
            } else {
                getDao().merge(getObjeto());
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoMedico
     */
    public MedicoDAO<Medico> getDaoMedico() {
        return daoMedico;
    }

    /**
     * @param daoMedico the daoMedico to set
     */
    public void setDaoMedico(MedicoDAO<Medico> daoMedico) {
        this.daoMedico = daoMedico;
    }

    /**
     * @return the daoPaciente
     */
    public PacienteDAO<PacienteDAO> getDaoPaciente() {
        return daoPaciente;
    }

    /**
     * @param daoPaciente the daoPaciente to set
     */
    public void setDaoPaciente(PacienteDAO<PacienteDAO> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    /**
     * @return the daoMedicamento
     */
    public MedicamentoDAO<MedicamentoDAO> getDaoMedicamento() {
        return daoMedicamento;
    }

    /**
     * @param daoMedicamento the daoMedicamento to set
     */
    public void setDaoMedicamento(MedicamentoDAO<MedicamentoDAO> daoMedicamento) {
        this.daoMedicamento = daoMedicamento;
    }

    /**
     * @return the receituario
     */
    public Receituario getReceituario() {
        return receituario;
    }

    /**
     * @param receituario the receituario to set
     */
    public void setReceituario(Receituario receituario) {
        this.receituario = receituario;
    }

    /**
     * @return the novoReceituario
     */
    public Boolean getNovoReceituario() {
        return novoReceituario;
    }

    /**
     * @param novoReceituario the novoReceituario to set
     */
    public void setNovoReceituario(Boolean novoReceituario) {
        this.novoReceituario = novoReceituario;
    }

    /**
     * @return the exame
     */
    public Exame getExame() {
        return exame;
    }

    /**
     * @param exame the exame to set
     */
    public void setExame(Exame exame) {
        this.exame = exame;
    }

    /**
     * @return the novoExame
     */
    public Boolean getNovoExame() {
        return novoExame;
    }

    /**
     * @param novoExame the novoExame to set
     */
    public void setNovoExame(Boolean novoExame) {
        this.novoExame = novoExame;
    }

}
