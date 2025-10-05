package br.itb.projeto.material_share.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.itb.projeto.material_share.model.entity.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

	/* LISTA TODOS OS REGISTROS DE ACORDO COM FILTRO INDICADO */
	/* APÓS O COMANDO "findBy[campo]" DEVE SER INFORMADO UM CAMPO VÁLIDO */
	/* O CAMPO VÁLIDO DEVE SER UM ATRIBUTO DA CLASSE DE ENTIDADE */
	List<Mensagem> findByEmail(String email);

	List<Mensagem> findByStatusMensagem(String statusMensagem);
	
	@Query("SELECT m FROM Mensagem m JOIN m.doacao d WHERE d.pessoa.id = :doadorId ORDER BY m.dataMensagem DESC")
    List<Mensagem> findMensagensByDoadorId(@Param("doadorId") Long doadorId);

}
