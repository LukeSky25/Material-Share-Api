package br.itb.projeto.material_share.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.material_share.model.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByStatusProduto(String statusProduto);

	Optional<Produto> findByCodigoBarras(String codigoBarras);

}
