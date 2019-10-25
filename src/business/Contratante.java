package business;

import java.io.Serializable;

public class Contratante extends Usuario implements Serializable {
	private static int contadorContratantes;
	private int numeroEventos;

	public Contratante(String nome, String senha, String email) {
		super(nome, senha, email);
	}

	public void criarEvento(Evento evento) {
		
	}

	public void fecharEvento(Evento evento) {
		
	}

	@Override
	public void addAvaliacao() {
	}

	@Override
	public float calculaMediaAvaliacao() {
		return 0;
	}
}
