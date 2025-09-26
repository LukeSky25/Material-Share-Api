package br.itb.projeto.material_share.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.material_share.model.entity.Pessoa;
import br.itb.projeto.material_share.model.entity.Usuario;
import br.itb.projeto.material_share.model.repository.PessoaRepository;
import br.itb.projeto.material_share.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;
	private UsuarioRepository usuarioRepository;
	
	public PessoaService(PessoaRepository pessoaRepository, UsuarioRepository usuarioRepository) {
		super();
		this.pessoaRepository = pessoaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	
	public Pessoa findById(long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		if (pessoa.isPresent()) {
			return pessoa.get();
		}
		
		return null;
	}
	
	public List<Pessoa> findAll() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas;
	}
	
	public List<Pessoa> findByTipo(String tipo) {
		List<Pessoa> pessoas = pessoaRepository.findByTipo(tipo);
		return pessoas;
	}
	
	public Pessoa findByUsuarioId(long usuarioId) {
		Optional<Pessoa> pessoa = pessoaRepository.findByUsuarioId(usuarioId);
		return pessoa.orElse(null);
	}
	
	@Transactional
	public Pessoa save(Pessoa pessoa) {
		
        long usuarioId = pessoa.getUsuario().getId();
     
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Tentativa de cadastrar pessoa com usuário de ID " + usuarioId + ", que não existe."));

        pessoa.setUsuario(usuario);
        pessoa.setStatusDoador("ATIVO");

        return pessoaRepository.save(pessoa);
		
	}
	
	@Transactional
	public Pessoa editar( long id, Pessoa pessoa) {
		Optional<Pessoa> _pessoa = pessoaRepository.findById(id);

		if (_pessoa.isPresent()) {
			Pessoa pessoaAtualizada = _pessoa.get();
		
			pessoaAtualizada.setNome(pessoa.getNome());
			pessoaAtualizada.setDataNascimento(pessoa.getDataNascimento());
			pessoaAtualizada.setCpf_cnpj(pessoa.getCpf_cnpj());
			pessoaAtualizada.setNome(pessoa.getNome());

			return pessoaRepository.save(pessoaAtualizada);
		}
		return null;
	}
	
}
