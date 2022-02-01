package br.mvso.empresa.boot.domain;

import br.mvso.empresa.boot.dao.DepartamentoDao;

import java.beans.PropertyEditorSupport;

public class ProfilePropertyEditor extends PropertyEditorSupport {
    DepartamentoDao dao;

    public ProfilePropertyEditor(DepartamentoDao dao){
        this.dao = dao;
    }

    @Override
    public void setAsText(String text){
        //Convertendo o id passado como String pelo front
        Long id = new Long(text);

        //Buscando o Departamento correpondente no banco.
        Departamento departamento = dao.findById(id);

        //Adicionando o objeto Departamento no controller através do método setValue da sperclasse
        super.setValue(departamento);
    }

}
