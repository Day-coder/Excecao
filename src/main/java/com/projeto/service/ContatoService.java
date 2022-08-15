package com.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.entities.Contato;
import com.projeto.repository.ContatoRepository;
import com.projeto.service.exceptions.EmailException;
import com.projeto.service.exceptions.MinhaExcecao;

@Service
public class ContatoService {
	@Autowired
	ContatoRepository repo;

	public Contato salvar(Contato contato) {
		if (!contato.getEmail().contains("@")) {
			throw new EmailException("Email inválido");
		}
		return repo.save(contato);
	}

	public List<Contato> consultarContatos() {
		List<Contato> contatos = (List<Contato>) repo.findAll();
		return contatos;

	}

	public Contato consultarContatoPorId(Long idcontato) {
		Optional<Contato> op = repo.findById(idcontato);
		Contato ct = op.orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));
		return ct;
	}

	public void excluirContato(Long id) {
//	Contato ct= consultarContatoPorId(id);//primeiro consulta
//	repo.delete(ct);// deleta
		// testando excecao
		Optional<Contato> op = repo.findById(id);
		Contato ct = op.orElseThrow(() -> new MinhaExcecao("O contato não existe"));
		repo.delete(ct);
		// repo.deleteById(id);
	}

	public Contato alterarContato(Long idcontato, Contato contato) {
		Contato ct = consultarContatoPorId(idcontato);
		ct.setEmail(contato.getEmail());
		ct.setNome(contato.getNome());
		ct.setFone(contato.getFone());
		return repo.save(ct);
	}

}
