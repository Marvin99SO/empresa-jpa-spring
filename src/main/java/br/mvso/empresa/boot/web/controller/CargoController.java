package br.mvso.empresa.boot.web.controller;

import br.mvso.empresa.boot.domain.Cargo;
import br.mvso.empresa.boot.domain.ProfilePropertyEditor;
import br.mvso.empresa.boot.service.CargoService;
import br.mvso.empresa.boot.service.DepartamentoService;
import br.mvso.empresa.boot.dao.DepartamentoDao;
import br.mvso.empresa.boot.domain.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    @Autowired
    private DepartamentoService dptosService;

    @Autowired
    private DepartamentoDao dptoDao;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
    public String cadastrar(Cargo cargo, ModelMap model){
        model.addAttribute("departamentos", dptosService.buscarTodos());
        return "/cargo/cadastro.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listar")
    public String listar(ModelMap model){
        model.addAttribute("cargos", service.buscarTodos());
        return "cargo/lista.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public String salvar(Cargo cargo, Departamento departamento){
        //cargo.setDepartamento(departamento);
        service.salvar(cargo);
        return "redirect:/cargos/cadastrar";
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder){
        binder.registerCustomEditor(Departamento.class, new ProfilePropertyEditor(dptoDao));
    }
}
