/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.model.Medicamento;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author matheus
 */
@Stateful
public class MedicamentoDAO<TIPO> extends DAOGenerico<Medicamento> implements Serializable {
    public MedicamentoDAO(){
        super();
        classePersistente = Medicamento.class;
        
        // definição da lista de ordenações
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("nome","Nome", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // vai pegar o segundo da lista de ordens
        // criando uma instancia do conversor
        converterOrdem = new ConverterOrdem();
        // associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);      
    }
}
