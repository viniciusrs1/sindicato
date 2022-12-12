package web.sindicato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Taxa;


public interface TaxaRepository extends JpaRepository<Taxa, Long> {

}