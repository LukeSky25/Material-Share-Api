package br.itb.projeto.material_share.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.material_share.model.entity.Categoria;
import java.util.List;


@Repository
public interface CategoriaRepository 
				 extends JpaRepository<Categoria, Long> {
	Optional<Categoria> findByNome(String nome);
}
