package web.sindicato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long> {

}