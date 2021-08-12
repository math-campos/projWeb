/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;
import br.edu.ifsul.model.Paciente;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author matheus
 */
@Named(value = "converterPaciente")
@RequestScoped
public class ConverterPaciente  implements Serializable, Converter {
    @PersistenceContext(unitName = "SistemaMedicoModelPU")
    private EntityManager em;    
    
    // converter o que vem da tela para um objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Paciente.class, Integer.parseInt(string));
    }

    // converte o objeto que vem do banco em uma string para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Paciente obj = (Paciente) t;
        return obj.getId().toString();
    }

}
