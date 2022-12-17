package web.sindicato.controller;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import web.sindicato.model.Status;

import web.sindicato.model.Empresa;
import web.sindicato.model.Socio;
import web.sindicato.model.Taxa;
import web.sindicato.model.filter.SocioFilter;
import web.sindicato.model.filter.TaxaFilter;
import web.sindicato.pagination.PageWrapper;
import web.sindicato.repository.EmpresaRepository;
import web.sindicato.repository.SocioRepository;
import web.sindicato.repository.TaxaRepository;
import web.sindicato.service.SocioService;
import web.sindicato.service.TaxaService;

@Controller
@RequestMapping("/socios")
public class SocioController {

	private static final Logger logger = LoggerFactory.getLogger(SocioController.class);

	@Autowired
	private SocioRepository socioRepository;

	@Autowired
	private TaxaRepository taxaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SocioService socioService;

	@Autowired
	private TaxaService taxaService;

	@GetMapping("/listar")
	public String listPartners(SocioFilter filtro, Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
		socioService.verificarTaxa();
		Page<Socio> pagina = socioRepository.pesquisar(filtro, pageable);
		PageWrapper<Socio> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "partners/listpartners";
	}

	@GetMapping("/buscar")
	public String searchPartners(SocioFilter filtro, Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
		socioService.verificarTaxa();
		Page<Socio> pagina = socioRepository.pesquisar(filtro, pageable);
		PageWrapper<Socio> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "partners/listpartners";
	}

	@GetMapping("/abrirtaxas")
	public String listTaxes(Socio socio, Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
		
		TaxaFilter filtro = new TaxaFilter();
		filtro.setCodigoSocio(socio.getCodigo());
		Page<Taxa> pagina = taxaRepository.pesquisar(filtro, pageable);
		PageWrapper<Taxa> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("nome", socio.getNome());
		model.addAttribute("pagina", paginaWrapper);
		return "partners/taxes";
	}

	@PostMapping("/alterartaxa")
	public String alterar(RedirectAttributes atributos, Taxa taxa) {
		
		taxa.setPago(true);
		taxaService.alterar(taxa);
		atributos.addAttribute("nome", taxa.getSocio().getNome());
		atributos.addAttribute(taxa.getSocio());
		return "redirect:/socios/abrirtaxas";
	}

	@GetMapping("/adicionar")
	public String opneAdd(Socio socio, Model model) {
		List<Empresa> empresas = empresaRepository.findByStatus(Status.ATIVO);
		model.addAttribute("empresas", empresas);
		return "partners/addpartners";
	}

	@PostMapping("/adicionar")
	public String cadastrar(@Valid Socio socio, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			logger.info("A socio recabida para cadastrar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			List<Empresa> empresas = empresaRepository.findByStatus(Status.ATIVO);
			model.addAttribute("empresas", empresas);
			return "socios/adicionar";
		} else {
			socioService.salvar(socio);
			return "redirect:/socios/listar";
		}
	}

}
