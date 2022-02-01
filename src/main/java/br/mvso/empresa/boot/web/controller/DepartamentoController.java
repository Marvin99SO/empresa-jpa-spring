package br.mvso.empresa.boot.web.controller;

import br.mvso.empresa.boot.domain.Departamento;
import br.mvso.empresa.boot.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
    public String cadastrar(Departamento departamento){
        return "/departamento/cadastro.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listar")
    public String listar(ModelMap model){
        model.addAttribute("departamentos", service.buscarTodos());
        return "/departamento/lista.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public String salvar(Departamento departamento){
        service.salvar(departamento);
        return "redirect:/departamentos/cadastrar";
    }


    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("departamento", service.buscarPorId(id));
        return "/departamento/cadastro";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(Departamento departamento){
        service.editar(departamento);
        return "redirect:/departamentos/cadastrar";
    }

    @RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
    public String excluir(@PathVariable("id") Long id, ModelMap model){
        System.out.println("Excluir em DepartamentoController.java");
        if(!service.departamentoTemCargos(id)){
            service.excluir(id);
        }
        return listar(model);
    }
}
