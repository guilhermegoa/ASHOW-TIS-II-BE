import java.time.LocalDateTime;

public class Evento {
	private static int contadorEvento = 0;
	
	private int id;
	private int capacidadeEsperada;
	private int quantidadeArtistas;
	private double valor;
	private String estilo;
	private boolean open;
	private LocalDateTime data;
	
	public Evento(int capacidadeEsperada, double valor, String estilo,
			LocalDateTime data) {
		contadorEvento++;
		this.id = contadorEvento;
		open = true;
		setCapacidadeEsperada(capacidadeEsperada);
		this.quantidadeArtistas = 0;
		setValor(valor);
		setEstilo(estilo);
		setData(data);
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void fecharCandidatura() {
		open = false;
	}
	
	public void abrirCandidatura() {
		if(!(this.data.isAfter(LocalDateTime.now()))) {
		open = true;
		}
	}

	public int getCapacidadeEsperada() {
		return capacidadeEsperada;
	}

	public void setCapacidadeEsperada(int capacidadeEsperada){	
		if(capacidadeEsperada <= 0) {
			capacidadeEsperada = 1;
		}
		this.capacidadeEsperada = capacidadeEsperada;
	}

	public int getQuantidadeArtistas() {
		return quantidadeArtistas;
	}

	public void aumentarArtistas() {
		this.quantidadeArtistas++;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		if(valor < 0) {
			valor = 0;
		}
		this.valor = valor;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public static int getContadorEvento() {
		return contadorEvento;
	}	
	
}
