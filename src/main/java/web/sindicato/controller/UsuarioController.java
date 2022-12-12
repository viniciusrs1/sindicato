package web.sindicato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	
	@GetMapping("/listar")
	public String listUsers() {
		
		return "users/listusers";
	}
	
	@GetMapping("/adicionar")
	public String addUsers() {
		return "users/addusers";
	}
}
