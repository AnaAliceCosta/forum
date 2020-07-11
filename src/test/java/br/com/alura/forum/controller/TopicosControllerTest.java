package br.com.alura.forum.controller;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import br.com.alura.forum.controller.dto.TokenDto;
import br.com.alura.forum.controller.form.LoginForm;
import br.com.alura.forum.modelo.Usuario;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:application.properties")
public class TopicosControllerTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void deveFuncionarURITopicos() {
		JsonPath jsonPath = get("/topicos").andReturn().jsonPath();
		List listaTopicos = jsonPath.getObject("content", List.class);
		assertTrue(listaTopicos.get(0).toString().contains("mensagem"));
	}

	@Test
	public void deveFuncionarURIAuth() {

		LoginForm usuario = usuarioBuilder();
		
		String json = new Gson().toJson(usuario);
		 JsonPath response = given()
				.contentType("application/json")
				.body(json)
				.when()
					.post("/auth")
				.andReturn().jsonPath();
		 TokenDto form = response.getObject("", TokenDto.class);
		  System.out.println(form.getAuthString());

		
	}

	private LoginForm usuarioBuilder() {
		LoginForm usuario = new LoginForm();
		usuario.setEmail("aluno@email.com");
		usuario.setSenha("123456");
		return usuario;
	}
}
