package br.itb.projeto.material_share.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.itb.projeto.material_share.model.entity.Categoria;
import br.itb.projeto.material_share.model.entity.Doacao;
import br.itb.projeto.material_share.model.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    private final MensagemService mensagemService;
	
	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository, MensagemService mensagemService) {
		super();
		this.categoriaRepository = categoriaRepository;
		this.mensagemService = mensagemService;
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
	
	@Transactional
    public Categoria update(long id, Categoria categoriaComDadosNovos) {
        
  
        Optional<Categoria> _categoria = categoriaRepository.findById(id);
        
 
        if (_categoria.isPresent()) {
            

            Categoria categoriaExistente = _categoria.get();
            
     
            categoriaExistente.setNome(categoriaComDadosNovos.getNome());
            categoriaExistente.setDescricao(categoriaComDadosNovos.getDescricao());
            categoriaExistente.setStatusCategoria(categoriaComDadosNovos.getStatusCategoria());
            
           
            return categoriaRepository.save(categoriaExistente);
        }
        

        return null;
    }
	
	@Transactional
    public Categoria inativar(long id) {

        Optional<Categoria> _categoria = categoriaRepository.findById(id);
        

        if (_categoria.isPresent()) {
            Categoria categoriaParaInativar = _categoria.get();
            

            categoriaParaInativar.setStatusCategoria("INATIVO");
            

            return categoriaRepository.save(categoriaParaInativar);
        }


        return null;
    }
	
}





