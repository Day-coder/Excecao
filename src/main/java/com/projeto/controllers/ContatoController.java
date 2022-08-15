package com.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.entities.Contato;
import com.projeto.repository.ContatoRepository;
import com.projeto.service.ContatoService;

@RestController
@RequestMapping("/")
public class ContatoController {
	@Autowired
	ContatoRepository repo;
	@Autowired
	ContatoService service;

	@GetMapping("/contatos")
	public ResponseEntity<List<Contato>> getContatos() {
		List <Contato> contatos= (List<Contato>) service.consultarContatos();
		return ResponseEntity.status(HttpStatus.OK).body(contatos);
	}
	@GetMapping("/contatos/{idcontato}")
	public ResponseEntity<Contato> getContatoById(@PathVariable("idcontato") Long idcontato) {
		//Contato ct= repo.findById(idcontato).get();
		//return ResponseEntity.status(HttpStatus.OK).body(ct);
//	Optional<Contato> contato= repo.findById(idcontato);
	//return contato.isPresent() ? ResponseEntity.ok(contato.get()) : ResponseEntity.notFound().build();
	return ResponseEntity.ok(service.consultarContatoPorId(idcontato));
	}
	
	@PostMapping("/contatos")
	public ResponseEntity<Contato> saveContato(@RequestBody Contato contato) {
		Contato ct1= service.salvar(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct1);
	}
//	@DeleteMapping("/contatos/{idcontato}")
//	public String deleteContato(@PathVariable("idcontato") int idcontato) {
//		return "Contato excluído" + idcontato;
//	}
	
	@DeleteMapping("/contatos/{idcontato}")
	public ResponseEntity<Void> deleteTeste(@PathVariable("idcontato") Long idcontato) {
	service.excluirContato(idcontato);
	return ResponseEntity.noContent().build();
	}
	
	@PutMapping("contatos/{idcontato}")
public ResponseEntity<Contato> alterarContato(@PathVariable("idcontato") Long idcontato,
		@RequestBody Contato contato) {
		return ResponseEntity.ok(service.salvar(contato));
	}

}
