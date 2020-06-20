package br.com.alura.forum.controller;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.alura.forum.Repository.CursoRepository;
import br.com.alura.forum.Repository.TopicoRepository;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.services.TopicoService;
import br.com.alura.forum.modelo.Topico;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private TopicoService service;

	@GetMapping
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort="id",direction = Direction.DESC, size = 10,page =0) Pageable page) {

		Page<Topico> topicos = service.buscaTopicosPaginados(nomeCurso, page);
		return TopicoDto.converter(topicos);
	}

	@PostMapping
	@Transactional
	@CacheEvict(value="topicosPaginados",allEntries = true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDto> listarTopico(@PathVariable Long id) {

		ResponseEntity<TopicoDto> topicoDetalhado = service.procuraTopicoDtoPorIdDoTopico(id);
		return topicoDetalhado;

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody TopicoForm form) {
		ResponseEntity<TopicoDto> resposta = new ResponseEntity<TopicoDto>(null);
		try {
			Topico topico = form.atualizar(id, topicoRepository);
			resposta = ResponseEntity.ok(new TopicoDto(topico));
		} catch (NullPointerException e) {
			resposta = ResponseEntity.notFound().build();
		} finally {
			return resposta;
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="topicosPaginados",allEntries = true)
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		topicoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
