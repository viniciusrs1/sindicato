package web.sindicato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping(value = {"/", "/index.html"} )
	public String index() {
		return "index";
	}
	
	@GetMapping(value = {"/login"} )
	public String login() {
		return "login";
	}
	
	@GetMapping(value = {"/listusers"} )
	public String listUsers() {
		return "users/listusers";
	}
	
	@GetMapping(value = {"/addusers"} )
	public String addUsers() {
		return "users/addusers";
	}
}