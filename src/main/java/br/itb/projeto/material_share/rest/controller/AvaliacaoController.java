package br.itb.projeto.material_share.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itb.projeto.material_share.model.entity.Avaliacao;
import br.itb.projeto.material_share.rest.response.MessageResponse;
import br.itb.projeto.material_share.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Avaliacao>> findAll() {
        List<Avaliacao> avaliacoes = avaliacaoService.findAll();
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Avaliacao avaliacao) {
        try {
            Avaliacao _avaliacao = avaliacaoService.save(avaliacao);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new MessageResponse("Avaliação enviada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erro ao salvar avaliação: " + e.getMessage()));
        }
    }
}