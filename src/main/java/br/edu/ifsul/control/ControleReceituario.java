/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.control;

import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.dao.ReceituarioDAO;
import br.edu.ifsul.model.Medicamento;
import br.edu.ifsul.model.Receituario;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author matheus
 */
@Named(value = "controleReceituario")
@ViewScoped
public class ControleReceituario implements Serializable {

    
    @EJB
    private ReceituarioDAO<Receituario> dao;
    private Receituario objeto;

    @EJB
    private MedicamentoDAO<Medicamento> daoMedicamento;
    private Medicamento medicamento;
    private int abaAtiva;

    public ControleReceituario() {

    }

    public void removerMedicamento(Medicamento obj) {
        getObjeto().getMedicamentos().remove(obj);
        Util.mensagemInformacao("Medicamento removido com sucesso!");
    }

    public void adicionarMedicamento() {
        if (!objeto.getMedicamentos().contains(medicamento)) {
            if (getMedicamento() != null) {
                getObjeto().getMedicamentos().add(getMedicamento());
                Util.mensagemInformacao("Medicamento adicionada com sucesso!");
            } else {
                Util.mensagemErro("Selecione o medicamento");
            }
        } else {
            Util.mensagemErro("Usuário já possui este medicamento");
        }
    }

    /**
     * @return the dao
     */
    public ReceituarioDAO<Receituario> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ReceituarioDAO<Receituario> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Receituario getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Receituario objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoMedicamento
     */
    public MedicamentoDAO<Medicamento> getDaoMedicamento() {
        return daoMedicamento;
    }

    /**
     * @param daoMedicamento the daoMedicamento to set
     */
    public void setDaoMedicamento(MedicamentoDAO<Medicamento> daoMedicamento) {
        this.daoMedicamento = daoMedicamento;
    }

    /**
     * @return the medicamento
     */
    public Medicamento getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the abaAtiva
     */
    public int getAbaAtiva() {
        return abaAtiva;
    }

    /**
     * @param abaAtiva the abaAtiva to set
     */
    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }
    
    public String listar() {
        return "/privado/receituario/listar?faces-redirect=true";
    }
    
    public void alterar(Object id) {
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

}
