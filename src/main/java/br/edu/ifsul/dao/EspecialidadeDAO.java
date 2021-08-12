/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.model.Especialidade;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author matheus
 */
@Stateful
public class EspecialidadeDAO<TIPO> extends DAOGenerico<Especialidade> implements Serializable {

    public EspecialidadeDAO() {
        super();
        classePersistente = Especialidade.class;
    }
}
