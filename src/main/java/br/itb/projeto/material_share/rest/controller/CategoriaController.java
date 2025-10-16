package br.itb.projeto.material_share.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itb.projeto.material_share.model.entity.Categoria;
import br.itb.projeto.material_share.rest.response.MessageResponse;
import br.itb.projeto.material_share.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private CategoriaService categoriaService;

	// Source -> Generate Constructor using Fields...
	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}

	@GetMapping("/test")
	public String getTest() {
		return "Olá, Categoria!";
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable long id) {
		Categoria categoria = categoriaService.findById(id);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Categoria>> findAll(){
		
		List<Categoria> categorias = categoriaService.findAll();
		
		return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
		
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Categoria categoria) {
		Categoria _categoria = categoriaService.save(categoria);
		
		if(_categoria == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("Erro: Já existe uma categoria com esse nome"));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Categoria criada com sucesso"));
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Categoria categoria) {
        

        Categoria categoriaAtualizada = categoriaService.update(id, categoria);
        

        if (categoriaAtualizada != null) {

            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Categoria atualizada com sucesso!"));
        }
        

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Erro: Categoria não encontrada com o ID fornecido."));
    }
	
	@PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable long id) {
        Categoria categoriaInativada = categoriaService.inativar(id);
        
        if (categoriaInativada != null) {
            return ResponseEntity.ok(new MessageResponse("Categoria inativada com sucesso!"));
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new MessageResponse("Erro: Categoria não encontrada."));
    }

}





