package tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Artista;
import business.Contratante;
import business.Usuario;
import dao.UsuarioDAO;

class UsuarioDAOTest {
	
	static UsuarioDAO userDAO;
	static Artista artista = new Artista("Gulio", "Gulião", "121212", "gu@gu.com", "rock", "solo");

	@BeforeEach
	void setUp() throws Exception{
		userDAO = new UsuarioDAO("artistaTest.bin");
	}
	
	@Test
	void add() {
		List<Usuario> artistas;	
		artistas = userDAO.getAll();	
		assertTrue(artistas.isEmpty());
		userDAO.add(artista);
		artistas = userDAO.getAll();
		assertFalse(artistas.isEmpty());
	}
	
	@Test
	void get() {
		userDAO.add(artista);
		assertEquals(userDAO.get("gu@gu.com"), artista);
		Contratante c = new Contratante("Eugênio", "eugenio123", "eu@geni.o"); 
		userDAO.add(c);
		assertEquals(userDAO.get("eu@geni.o"), c);
		assertEquals(userDAO.get("mat@mat.com"), null);
	}
	
	@Test
	void remove() {
		userDAO.add(artista);
		assertTrue(userDAO.remove(artista));
		List<Usuario> artistas = userDAO.getAll();
		assertTrue(artistas.isEmpty());
		assertFalse(userDAO.remove(artista));
	}
	
	@Test
	void update() {
		userDAO.add(artista);
		assertFalse(userDAO.update(new Artista("William", "Bigode", "231100", "william@thiago.com", "Sertanejo", "Solo")));
		assertNotEquals((userDAO.get("gu@gu.com").getNome()), "Gulinho");
		assertTrue(userDAO.update(new Artista("Gulinho", "Gulião", "121212", "gu@gu.com", "rock", "solo")));
		assertEquals((userDAO.get("gu@gu.com").getNome()), "Gulinho");
	}

}
