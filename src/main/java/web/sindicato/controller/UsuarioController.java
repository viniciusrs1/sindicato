package web.sindicato.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.sindicato.repository.PapelRepository;
import web.sindicato.service.CadastroUsuarioService;
import web.sindicato.controller.UsuarioController;
import web.sindicato.model.Papel;
import web.sindicato.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@GetMapping("/listar")
	public String listUsers(Usuario usuario, Model model) {
		List<Papel> papeis = papelRepository.findAll();
		model.addAttribute("todosPapeis", papeis);
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
