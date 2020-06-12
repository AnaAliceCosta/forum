package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.Repository.TopicoRepository;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@Controller
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@RequestMapping("/topicos")
	@ResponseBody
	public List<TopicoDto> lista() {
		List<TopicoDto> topicos = criaListaDeTopicos();
		return topicos;
	}

	private List<TopicoDto> criaListaDeTopicos() {

		List<Topico> topicos = topicoRepository.findAll();
		return TopicoDto.converter(topicos);
	}
}
