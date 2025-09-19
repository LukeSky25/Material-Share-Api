package br.itb.projeto.material_share.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.material_share.model.entity.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Pessoa findByUsuarioEmail(String email);
	
	List<Pessoa> findByTipo(String string);
	
}
