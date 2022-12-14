package web.sindicato.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import web.sindicato.pagination.PageWrapper;
import web.sindicato.repository.EmpresaRepository;
import web.sindicato.service.EmpresaService;
import web.sindicato.model.Status;

import web.sindicato.model.Empresa;
import web.sindicato.model.filter.EmpresaFilter;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
	
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	
	@GetMapping("/listar")
	public String listCompanies(EmpresaFilter filtro, Model model, 
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
	

	Page<Empresa> pagina = empresaRepository.pesquisar(filtro, pageable);
	PageWrapper<Empresa> paginaWrapper = new PageWrapper<>(pagina, request);
	model.addAttribute("pagina", paginaWrapper);
		return "companies/listcompanies";
	}
	
	
	@GetMapping("/buscar")
	public String searchCompanies(EmpresaFilter filtro, Model model, 
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
	

	Page<Empresa> pagina = empresaRepository.pesquisar(filtro, pageable);
	PageWrapper<Empresa> paginaWrapper = new PageWrapper<>(pagina, request);
	model.addAttribute("pagina", paginaWrapper);
		return "companies/listcompanies";
	}
	
	
	
	@GetMapping("/adicionar")
	public String opneAdd(Empresa empresa) {
		return "companies/addcompanies";
	}
	
	@PostMapping("/adicionar")
	public String cadastrar(@Valid Empresa empresa, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("A empresa recabida para cadastrar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "empresas/adicionar";
		} else {
			empresaService.salvar(empresa);
			return "redirect:/empresas/listar";
		}
	}
	
	@PostMapping("/abriralterar")
	public String openEdit(Empresa empresa) {
		return "companies/editcompany";
	}
	
	@PostMapping("/alterar")
	public String alterar(@Valid Empresa empresa, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("A empresa recabida para alterar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "companies/editcompany";
		} else {
			empresaService.alterar(empresa);
			return "redirect:/empresas/listar";
		}
	}
	
	@PostMapping("/remover")
	public String remover(Empresa empresa) {
		empresa.setStatus(Status.INATIVO);
		empresaService.alterar(empresa);
		return "redirect:/empresas/listar";
	}

}
