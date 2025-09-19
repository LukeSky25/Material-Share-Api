package br.itb.projeto.material_share.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.material_share.model.entity.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

	List<Promocao> findByStatusPromocao(String string);

}
