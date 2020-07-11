package br.com.alura.forum.controller.builder;

import br.com.alura.forum.controller.form.LoginForm;

public class LoginFormBuilder {
	public LoginForm build() {
		LoginForm usuario = new LoginForm();
		usuario.setEmail("aluno@email.com");
		usuario.setSenha("123456");
		return usuario;
	}
}
