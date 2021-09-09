/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.model.Consulta;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author matheus
 */
@Stateful
public class ConsultaDAO<TIPO> extends DAOGenerico<Consulta> implements Serializable  {
    public ConsultaDAO(){
        super();
        classePersistente = Consulta.class;
        
        // definição da lista de ordenações
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("paciente.nome","Paciente", "like"));
        listaOrdem.add(new Ordem("medico.nome","Medico", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // vai pegar o segundo da lista de ordens
        // criando uma instancia do conversor
        converterOrdem = new ConverterOrdem();
        // associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);      
    } 
        
    @Override
    public Consulta localizar(Object id) throws Exception {
        
        Consulta objeto = em.find(Consulta.class, id);
        //Deve-se inicializar a coleção ou coleções do objeto para não dar um erro de lazy inicialization exception
        objeto.getExames().size();
        objeto.getReceituarios().size();
        
        return objeto;
    }
}
