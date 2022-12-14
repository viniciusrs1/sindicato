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

import web.sindicato.model.Empresa;
import web.sindicato.model.Socio;
import web.sindicato.model.filter.SocioFilter;
import web.sindicato.pagination.PageWrapper;
import web.sindicato.repository.SocioRepository;
import web.sindicato.service.SocioService;

@Controller
@RequestMapping("/socios")
public class SocioController {
	
	private static final Logger logger = LoggerFactory.getLogger(SocioController.class);
	
	
	@Autowired
	private SocioRepository socioRepository;
	
	@Autowired
	private SocioService socioService;
	
	
	@GetMapping("/listar")
	public String listPartners(SocioFilter filtro, Model model, 
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
	

	Page<Socio> pagina = socioRepository.pesquisar(filtro, pageable);
	PageWrapper<Socio> paginaWrapper = new PageWrapper<>(pagina, request);
	model.addAttribute("pagina", paginaWrapper);
		return "partners/listpartners";
	}
	
	
	@GetMapping("/buscar")
	public String searchPartners(SocioFilter filtro, Model model, 
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
	

	Page<Socio> pagina = socioRepository.pesquisar(filtro, pageable);
	PageWrapper<Socio> paginaWrapper = new PageWrapper<>(pagina, request);
	model.addAttribute("pagina", paginaWrapper);
		return "partners/listpartners";
	}
	
	
	
	@GetMapping("/adicionar")
	public String opneAdd(Socio socio) {
		return "partners/addpartners";
	}
	
	@PostMapping("/adicionar")
	public String cadastrar(@Valid Socio socio, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("A socio recabida para cadastrar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "socios/adicionar";
		} else {
			socioService.salvar(socio);
			return "redirect:/socios/listar";
		}
	}
	
	@PostMapping("/abrirtaxas")
	public String openTaxes(Socio socio) {
		return "partners/taxes";
	}
	
	@PostMapping("/taxas")
	public String alterar(@Valid Socio socio, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("O socio recabida para alterar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "partners/taxes";
		} else {
			socioService.alterar(socio);
			return "redirect:/socios/taxas";
		}
	}

}
