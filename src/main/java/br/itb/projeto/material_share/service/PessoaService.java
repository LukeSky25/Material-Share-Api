package br.itb.projeto.material_share.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import br.itb.projeto.material_share.model.entity.Pessoa;
import br.itb.projeto.material_share.model.entity.Usuario;
import br.itb.projeto.material_share.model.repository.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private UsuarioService usuarioService; 


    public PessoaService(PessoaRepository pessoaRepository, UsuarioService usuarioService) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioService = usuarioService;
    }

    public Pessoa findById(long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElse(null);
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public List<Pessoa> findByTipo(String tipo) {
        return pessoaRepository.findByTipo(tipo);
    }

    public Pessoa findByUsuarioId(long usuarioId) {
        Optional<Pessoa> pessoa = pessoaRepository.findByUsuarioId(usuarioId);
        return pessoa.orElse(null);
    }
    
    @Transactional
	public Pessoa save(Pessoa pessoa) {
		
        long usuarioId = pessoa.getUsuario().getId();
   
        Usuario usuario = usuarioService.findById(usuarioId);
        if (usuario == null) {
            throw new RuntimeException("Tentativa de cadastrar pessoa com usuário de ID " + usuarioId + ", que não existe.");
        }

        pessoa.setUsuario(usuario);
        pessoa.setStatusDoador("ATIVO");

        return pessoaRepository.save(pessoa);
		
	}


    @Transactional
    public Pessoa editar(long id, Pessoa dadosRecebidos) {
        Optional<Pessoa> _pessoa = pessoaRepository.findById(id);

        if (_pessoa.isPresent()) {
            Pessoa pessoaParaAtualizar = _pessoa.get();
            Usuario usuarioParaAtualizar = pessoaParaAtualizar.getUsuario();

           
            pessoaParaAtualizar.setNome(dadosRecebidos.getNome());
            pessoaParaAtualizar.setDataNascimento(dadosRecebidos.getDataNascimento());
            pessoaParaAtualizar.setCelular(dadosRecebidos.getCelular());
            pessoaParaAtualizar.setCpf_cnpj(dadosRecebidos.getCpf_cnpj());
            pessoaParaAtualizar.setCep(dadosRecebidos.getCep());
            pessoaParaAtualizar.setNumeroResidencia(dadosRecebidos.getNumeroResidencia());
            pessoaParaAtualizar.setComplemento(dadosRecebidos.getComplemento());


            if (dadosRecebidos.getUsuario() != null) {
                

                usuarioParaAtualizar.setNome(dadosRecebidos.getNome()); 
                

                if (dadosRecebidos.getUsuario().getEmail() != null) {
                    String novoEmail = dadosRecebidos.getUsuario().getEmail();
                    usuarioParaAtualizar.setEmail(novoEmail);
                }
                

                usuarioService.update(usuarioParaAtualizar);
            }


            return pessoaRepository.save(pessoaParaAtualizar);
        }
        return null;
    }
}