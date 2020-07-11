package br.com.alura.forum.controller.dto;

public class TokenDto {

	private String token;
	private String authString;

	public TokenDto(String token, String string) {
		this.token = token;
		this.authString = string;
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public String getAuthString() {
		return authString;
	}


}
