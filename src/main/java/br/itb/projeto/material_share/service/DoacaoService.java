package br.itb.projeto.material_share.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.material_share.model.entity.Doacao;
import br.itb.projeto.material_share.model.entity.Produto;
import br.itb.projeto.material_share.model.repository.DoacaoRepository;
import jakarta.transaction.Transactional;

@Service
public class DoacaoService {

	private DoacaoRepository doacaoRepository;
	
	public DoacaoService(DoacaoRepository doacaoRepository) {
		super();
		this.doacaoRepository = doacaoRepository;
	}
	
	public Doacao findById(long id) {
		Optional<Doacao> doacao = doacaoRepository.findById(id);
		
		if(doacao.isPresent()) {
			return doacao.get();
		}
		
		return null;
	}
	
	public List<Doacao> findAll() {
		List<Doacao> doacoes = doacaoRepository.findAll();
		return doacoes;
	}
	
	public List<Doacao> findByStatusDoacao(String statusDoacao) {
		List<Doacao> doacoes = doacaoRepository.findByStatusDoacao(statusDoacao);
		return doacoes;
	}
	
	public List<Doacao> findbyNome(String nome) {
		return doacaoRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Doacao> findByCategoria(Long categoriaId) {
		return doacaoRepository.findByCategoriaId(categoriaId);
	}
	
	
	
	@Transactional
	public Doacao create(Doacao doacao) {
		doacao.setFoto(null);
		doacao.setDataCadastro(LocalDateTime.now());
		doacao.setStatusDoacao("ATIVO");
		
		return doacaoRepository.save(doacao);
	}
	
	@Transactional
	public Doacao createComFoto(MultipartFile file, Doacao doacao) {
		
		if (file != null && file.getSize() > 0) {
			try {
				doacao.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			doacao.setFoto(null);
		}
		
		doacao.setDataCadastro(LocalDateTime.now());
		doacao.setStatusDoacao("ATIVO");
		
		
		return doacaoRepository.save(doacao);
	}
	
	@Transactional
	public Doacao inativar(long id, String novoStatus) {
		Optional<Doacao> _doacao = doacaoRepository.findById(id);
		
		if(_doacao.isPresent()) {
			Doacao doacaoAtualizada = _doacao.get();
			
			if (!novoStatus.equalsIgnoreCase("INATIVO") && 
				!novoStatus.equalsIgnoreCase("SOLICITADO") &&
				!novoStatus.equalsIgnoreCase("DOADO")) {
			    throw new IllegalArgumentException("Status inválido. Use 'INATIVO' ou 'DOADO'.");
			}

			doacaoAtualizada.setStatusDoacao(novoStatus.toUpperCase());
			return doacaoRepository.save(doacaoAtualizada);
		}

		return null;
	}
	
	@Transactional
	public Doacao reativar(long id) {
		Optional<Doacao> _doacao = 
				doacaoRepository.findById(id);
		
		if (_doacao.isPresent()) {
			Doacao doacaoAtualizada = _doacao.get();
			doacaoAtualizada.setStatusDoacao("ATIVO");
			
			return doacaoRepository.save(doacaoAtualizada);
		}
		return null;
	}
	
	@Transactional
	public Doacao editar(MultipartFile file, long id, Doacao doacao) {
		Optional<Doacao> _doacao = doacaoRepository.findById(id);
		
		if (_doacao.isPresent()) {
			Doacao doacaoAtualizada = _doacao.get();
			
			if (doacao.getNome() != null)
				doacaoAtualizada.setNome(doacao.getNome());

	        if (doacao.getDescricao() != null)
	        	doacaoAtualizada.setDescricao(doacao.getDescricao());

	        if (doacao.getCep() != null)
	        	doacaoAtualizada.setCep(doacao.getCep());

	        if (doacao.getComplemento() != null)
	        	doacaoAtualizada.setComplemento(doacao.getComplemento());
	        
	        if (doacao.getNumeroResidencia() != null)
	        	doacaoAtualizada.setNumeroResidencia(doacao.getNumeroResidencia());
	        
	        if (doacao.getQuantidade() != null)
	        	doacaoAtualizada.setQuantidade(doacao.getQuantidade());
	        
	        if (doacao.getCategoria() != null)
	        	doacaoAtualizada.setCategoria(doacao.getCategoria());

	        if (doacao.getPessoa() != null)
	        	doacaoAtualizada.setPessoa(doacao.getPessoa());

	        if (doacao.getStatusDoacao() != null)
	        	doacaoAtualizada.setStatusDoacao(doacao.getStatusDoacao());

	        if (file != null && file.getSize() > 0) {
	            try {
	            	doacaoAtualizada.setFoto(file.getBytes());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	        return doacaoRepository.save(doacaoAtualizada);
	    }

	    return null;
	}
}
