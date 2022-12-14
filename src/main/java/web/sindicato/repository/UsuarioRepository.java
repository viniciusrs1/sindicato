package web.sindicato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Usuario;
import web.sindicato.repository.helper.usuario.UsuarioQueries;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioQueries {
	
	Usuario findByNomeUsuarioIgnoreCase(String nomeUsuario);
	
}
