package br.itb.projeto.material_share.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import br.itb.projeto.material_share.model.entity.Pessoa;
import br.itb.projeto.material_share.rest.response.MessageResponse;
import br.itb.projeto.material_share.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	private PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		super();
		this.pessoaService = pessoaService;
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable long id) {
		Pessoa pessoa = pessoaService.findById(id);
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> pessoas = pessoaService.findAll();
		
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	@GetMapping("/findByTipo/{tipo}")
	public ResponseEntity<List<Pessoa>> findByTipo(@PathVariable String tipo) {
		List<Pessoa> pessoas = pessoaService.findByTipo(tipo);
		
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
		
		Pessoa _pessoa = pessoaService.save(pessoa);
		
		return new ResponseEntity<>(_pessoa, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editar(@PathVariable long id, @ModelAttribute Pessoa pessoa) {
		Pessoa _pessoa = pessoaService.editar(id, pessoa);
		
		return ResponseEntity.ok().body(new MessageResponse("Dados atualizados com sucesso"));
	}
}
