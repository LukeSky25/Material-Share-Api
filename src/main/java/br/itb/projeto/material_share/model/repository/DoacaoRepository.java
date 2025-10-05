package br.itb.projeto.material_share.model.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.itb.projeto.material_share.model.entity.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
	
	List<Doacao> findByStatusDoacao(String statusDoacao);
	
	List<Doacao> findByNomeContainingIgnoreCase(String nome);
	
	List<Doacao> findByCategoriaId(Long categoriaId);
	
	List<Doacao> findByPessoaId(Long pessoaId);
	
	List<Doacao> findByPessoaIdAndStatusDoacaoIn(Long pessoaId, Collection<String> statuses);
	
	@Query("SELECT DISTINCT d FROM Doacao d JOIN Mensagem m ON d.id = m.doacao.id " +
		       "WHERE m.pessoa.id = :beneficiarioId AND d.statusDoacao IN ('ATIVO', 'SOLICITADO')")
		List<Doacao> findDoacoesSolicitadasPorBeneficiario(@Param("beneficiarioId") Long beneficiarioId);
	
	@Query("SELECT d FROM Doacao d WHERE " +
	           "d.statusDoacao = 'ATIVO' AND " +
	           "(:nome IS NULL OR d.nome LIKE %:nome%) AND " +
	           "(:categorias IS NULL OR d.categoria.id IN :categorias)")
	    List<Doacao> filtrarDoacoes(
	        @Param("nome") String nome,
	        @Param("categorias") List<Long> categorias
	    );
}
