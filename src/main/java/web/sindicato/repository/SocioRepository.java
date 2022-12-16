package web.sindicato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Socio;
import web.sindicato.repository.helper.socio.SocioQueries;

public interface SocioRepository extends JpaRepository<Socio, Long>, SocioQueries {

	
	
}