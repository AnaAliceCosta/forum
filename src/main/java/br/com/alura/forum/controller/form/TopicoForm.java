package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.alura.forum.Repository.CursoRepository;
import br.com.alura.forum.Repository.TopicoRepository;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

public class TopicoForm {
	@NotEmpty @NotNull @Length(min=5)
	private String titulo;
	@NotEmpty @NotNull @Length(min=10)
	private String mensagem;
	@NotEmpty @NotNull @Length(min=5)
	private String nomeCurso;
	
	
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	
	public Topico converter(CursoRepository repository) {
		Curso curso = repository.findByNome(nomeCurso);
		return new Topico(titulo,mensagem,curso );
	}
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		topico.setTitulo(titulo);
		topico.setMensagem(this.mensagem);
		
		return topico;
	}
	
	
	
}
