package br.itb.projeto.material_share.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.itb.projeto.material_share.model.entity.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
	
	List<Doacao> findByStatusDoacao(String statusDoacao);
	
	List<Doacao> findByNome(String nome);
	
	List<Doacao> findByCategoriaId(Long categoriaId);
	
}
