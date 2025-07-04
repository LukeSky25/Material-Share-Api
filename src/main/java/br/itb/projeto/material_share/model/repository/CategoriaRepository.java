package br.itb.projeto.material_share.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.material_share.model.entity.Categoria;

@Repository
public interface CategoriaRepository 
				 extends JpaRepository<Categoria, Long> {

}
