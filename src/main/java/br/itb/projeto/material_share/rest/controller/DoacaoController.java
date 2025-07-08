package br.itb.projeto.material_share.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itb.projeto.material_share.model.entity.Doacao;
import br.itb.projeto.material_share.model.repository.DoacaoRepository;
import br.itb.projeto.material_share.rest.response.MessageResponse;
import br.itb.projeto.material_share.service.DoacaoService;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

	

	private DoacaoRepository doacaoRepository;
	
	public DoacaoController(DoacaoService doacaoService) {
		super();
		this.doacaoService = doacaoService;
	}
	
	@GetMapping("/test")
	public String getTest() {
		return "Olá, Doacao!";
	}
	
	@PostMapping("/createSemFoto")
	public ResponseEntity<?> create(@ModelAttribute Doacao doacao) {
		
		Doacao _doacao = doacaoService.create(doacao);
		
		if(_doacao == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Doacao já cadastrada!"));
		}
		
		return ResponseEntity.ok().body(new MessageResponse("Doacao cadastrada com sucesso!"));
	}
	
	

}
	

