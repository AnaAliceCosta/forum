package br.com.alura.forum.controller.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order.Direction;

import br.com.alura.forum.Repository.CursoRepository;
import br.com.alura.forum.Repository.TopicoRepository;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Topico;

@Service
public class TopicoService {
	@Autowired
	TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	@Cacheable(value="topicosPaginados")
	public Page<Topico> buscaTopicosPaginados(String nomeCurso, Pageable page) {
		Page<Topico> topicos;

		if (nomeCurso == null) {
			topicos = topicoRepository.findAll(page);
		} else {
			topicos = topicoRepository.findByCursoNome(nomeCurso, page);
		}
		return topicos;
	}

	public ResponseEntity<TopicoDto> procuraTopicoDtoPorIdDoTopico(Long id) {
	
			Optional<Topico> topico = topicoRepository.findById(id);
			if(topico.isPresent()) {
				return ResponseEntity.ok(new TopicoDto(topico.get()));
			}
			return ResponseEntity.notFound().build();

	}
}
