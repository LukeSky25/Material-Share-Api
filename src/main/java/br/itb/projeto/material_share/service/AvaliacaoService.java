package br.itb.projeto.material_share.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.itb.projeto.material_share.model.entity.Avaliacao;
import br.itb.projeto.material_share.model.repository.AvaliacaoRepository;
import br.itb.projeto.material_share.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }
    
    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    @Transactional
    public Avaliacao save(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }
    
    
}