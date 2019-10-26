package tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Endereco;
import business.Evento;
import dao.EventoDAO;

class EventoDAOTest {

	static EventoDAO eventDAO;
	static Endereco endereco = new Endereco("3214800", "Paraíba", "210", "Savassi", "Belo Horizonte", "MG", "");
	static Evento evento = new Evento("Festa Fantasia", 160, 2000, "eletrônica", LocalDateTime.now(), endereco);

	@BeforeEach
	void setUp() throws Exception{
		eventDAO = new EventoDAO("eventoTest.bin");
	}
	
	@Test
	void add() {
		List<Evento> eventos;	
		eventos = eventDAO.getAll();	
		assertTrue(eventos.isEmpty());
		eventDAO.add(evento);
		eventos = eventDAO.getAll();
		assertFalse(eventos.isEmpty());
	}
	
	@Test
	void get() {
		eventDAO.add(evento);
		assertEquals(eventDAO.get("Festa Fantasia"), evento);
		Evento evento2 = new Evento("Reveillon", 5000, 4500, "Pop", LocalDateTime.now(), endereco);
		eventDAO.add(evento2);
		assertEquals(eventDAO.get("Reveillon"), evento2);
		assertEquals(eventDAO.get("Parque Ita"), null);
	}
	
	@Test
	void remove() {
		eventDAO.add(evento);
		assertTrue(eventDAO.remove(evento));
		List<Evento> eventos = eventDAO.getAll();
		assertTrue(eventos.isEmpty());
		assertFalse(eventDAO.remove(evento));
	}
	
	@Test
	void update() {
		eventDAO.add(evento);
		assertFalse(eventDAO.update(new Evento("Reveillon", 5000, 4500, "Pop", LocalDateTime.now(), endereco)));
		assertNotEquals((eventDAO.get("Festa Fantasia").getEstilo()), "Pop");
		assertTrue(eventDAO.update(new Evento("Festa Fantasia", 140, 1500, "Pop", LocalDateTime.now(), endereco)));
		assertEquals((eventDAO.get("Festa Fantasia").getEstilo()), "Pop");
	}

}
