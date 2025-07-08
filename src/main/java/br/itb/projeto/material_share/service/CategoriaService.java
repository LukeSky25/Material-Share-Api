package br.itb.projeto.material_share.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.itb.projeto.material_share.model.entity.Categoria;
import br.itb.projeto.material_share.model.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}
	
	public Categoria findById(long id) {

		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (categoria.isPresent()) {
			return categoria.get();
		}

		return null;
	}
	
	public List<Categoria> findAll(){
		List<Categoria> categorias = categoriaRepository.findAll();
	
		return categorias;
	}
	
	@Transactional
	public Categoria save(Categoria categoria) {
		Optional<Categoria> _categoria = categoriaRepository.findByNome(categoria.getNome());
		
		if(_categoria.isEmpty()) {
			categoria.setStatusCategoria("ATIVO");
			
			return categoriaRepository.save(categoria);
		}
		
		return null;
	}
}





