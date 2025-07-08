package br.itb.projeto.material_share.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.material_share.model.entity.Doacao;
import br.itb.projeto.material_share.model.entity.Produto;
import br.itb.projeto.material_share.rest.response.MessageResponse;
import br.itb.projeto.material_share.service.DoacaoService;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

	public DoacaoController(DoacaoService doacaoService) {
		super();
		this.doacaoService = doacaoService;
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Doacao> findById(@PathVariable long id) {
		Doacao doacao = doacaoService.findById(id);
		
		return new ResponseEntity<Doacao>(doacao, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Doacao>> findAll() {
		List<Doacao> doacoes = doacaoService.findAll();
		
		return new ResponseEntity<List<Doacao>>(doacoes, HttpStatus.OK);
	}
	
	@GetMapping("/findByStatus/{statusDoacao}")
	public ResponseEntity<List<Doacao>> findByStatuDoacao(@PathVariable String statusDoacao) {
		List<Doacao> doacoes = doacaoService.findByStatusDoacao(statusDoacao);
		
		return new ResponseEntity<List<Doacao>>(doacoes, HttpStatus.OK);
	}
	
	@GetMapping("/findByNome/{nome}")
	public ResponseEntity<List<Doacao>> findByNome(@PathVariable String nome) {
		List<Doacao> doacoes = doacaoService.findbyNome(nome);
		
		return new ResponseEntity<List<Doacao>>(doacoes, HttpStatus.OK);
	}
	
	@GetMapping("/findByCategoria/{categoria_id}")
	public ResponseEntity<List<Doacao>> findByCategoria(@PathVariable Long categoria_id) {
		List<Doacao> doacoes = doacaoService.findByCategoria(categoria_id);
		
		return new ResponseEntity<List<Doacao>>(doacoes, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Doacao doacao) {
		
		Doacao _doacao = doacaoService.create(doacao);
		
		if(_doacao == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Doacao já cadastrada!"));
		}
		
		return ResponseEntity.ok().body(new MessageResponse("Doacao cadastrada com sucesso!"));
	}
	
	@PostMapping("/createComFoto")
	public ResponseEntity<?> createComFoto(
			@RequestParam(required = false) MultipartFile file,
			@ModelAttribute Doacao doacao) {
		
		doacaoService.createComFoto(file, doacao);
		
		return ResponseEntity.ok().body(new MessageResponse("Doacao cadastrada com sucesso!"));
	}
	
	
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> edit(@PathVariable long id,
			@RequestParam(required = false) MultipartFile file,
			@ModelAttribute Doacao doacao) {

		doacaoService.editar(file, id, doacao);

		return ResponseEntity.ok()
				.body(new MessageResponse("Doação editada com sucesso!"));
	}
	
	@PutMapping("/inativar/{id}/{novoStatus}")
	public ResponseEntity<Doacao> inativar(@PathVariable long id, @PathVariable String novoStatus) {

		Doacao _doacao = doacaoService.inativar(id, novoStatus);

		return new ResponseEntity<Doacao>(_doacao, HttpStatus.OK);
	}

	@PutMapping("/reativar/{id}")
	public ResponseEntity<Doacao> reativar(@PathVariable long id) {

		Doacao _doacao = doacaoService.reativar(id);

		return new ResponseEntity<Doacao>(_doacao, HttpStatus.OK);
	}
	

}
	

