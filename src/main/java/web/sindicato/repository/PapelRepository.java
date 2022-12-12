package web.sindicato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
}
