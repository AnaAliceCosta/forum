package br.com.alura.forum.controller;

import static org.junit.Assert.assertTrue;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TopicosControllerTest {

	@Autowired
	TopicosController topicosController;
	
	
	@Test
	public void TesteInicial() {
		assertTrue(true);
	}
}
