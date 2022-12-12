package web.sindicato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNomeUsuarioIgnoreCase(String nomeUsuario);
	
}
