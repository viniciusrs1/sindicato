package web.sindicato.repository.helper.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sindicato.model.Usuario;
import web.sindicato.model.filter.UsuarioFilter;

public interface UsuarioQueries {

	Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable);
}

