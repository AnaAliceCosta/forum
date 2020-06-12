package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@Controller
public class TopicosController {
	
	@RequestMapping("/topicos")
	@ResponseBody
	public List<TopicoDto> lista() {
		List<TopicoDto> topicos = criaListaDeTopicos();
		return topicos;
	}

	private List<TopicoDto> criaListaDeTopicos() {
		 Topico topico = new Topico("duvida","duvidaCon Spring",new Curso("Sring 1","Programacao"));
		 return TopicoDto.converter(Arrays.asList(topico,topico,topico));
	}
}
