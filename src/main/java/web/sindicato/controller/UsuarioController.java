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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.sindicato.model.Papel;
import web.sindicato.model.Usuario;
import web.sindicato.model.filter.UsuarioFilter;
import web.sindicato.pagination.PageWrapper;
import web.sindicato.repository.PapelRepository;
import web.sindicato.repository.UsuarioRepository;
import web.sindicato.service.CadastroUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@GetMapping("/listar")
	public String listUsers(UsuarioFilter filtro, Model model, 
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
	

	Page<Usuario> pagina = usuarioRepository.pesquisar(filtro, pageable);
	PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(pagina, request);
	model.addAttribute("pagina", paginaWrapper);
		return "users/listusers";
	}
	
	@GetMapping("/adicionar")
	public String addUsers(Usuario usuario, Model model) {
		
		List<Papel> papeis = papelRepository.findAll();
		model.addAttribute("todosPapeis", papeis);
		return "users/addusers";
	}
	
	@PostMapping("/adicionar")
	public String cadastrarNovoUsuario(@Valid Usuario usuario, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			logger.info("O usuario recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			List<Papel> papeis = papelRepository.findAll();
			model.addAttribute("todosPapeis", papeis);
			return "users/addusers";
		} else {
			usuario.setAtivo(true);
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			cadastroUsuarioService.salvar(usuario);
			return "redirect:/usuarios/listar";
		}
	}
}
