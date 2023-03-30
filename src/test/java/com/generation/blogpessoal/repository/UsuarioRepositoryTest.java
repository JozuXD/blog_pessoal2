package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();

		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@gmail.com", "12345678", "imagem4"));

		usuarioRepository.save(new Usuario(0L, "Jozu Oliveira", "jozu@gmail.com", "12345678", "imagem1"));

		usuarioRepository.save(new Usuario(0L, "Lauro Silva", "Lauro@gmail.com", "12345678", "imagem2"));

		usuarioRepository.save(new Usuario(0L, "Ag Louco Silva", "agntc@gmail.com", "12345678", "imagem3"));

		
	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("jozu@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("jozu@gmail.com"));
	}
	
	@Test
	@DisplayName("retorna 3 usuarios")
	public void deveRetornaTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3,listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Lauro Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Ag Louco Silva"));
	}
	
	public void end() {
		usuarioRepository.deleteAll();
	}

}
