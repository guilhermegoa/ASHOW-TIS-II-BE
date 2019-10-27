package Ashow.business;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Evento implements Serializable {
    private static int contadorEvento = 0;
    private int id;
    private int capacidadeEsperada;
    private int quantidadeArtistas;
    private double valor;
    private String estilo;
    private String nome;
    private boolean open;
    private LocalDateTime data;
    private Endereco endereco;

    public Evento(String nome, int capacidadeEsperada, double valor, String estilo, LocalDateTime data, Endereco endereco) {
        setNome(nome);
        setValor(valor);
        setEstilo(estilo);
        setData(data);
        setCapacidadeEsperada(capacidadeEsperada);
        somaUMContadorEvento();
        setId(getContadorEvento());
        setOpen(true);
        setQuantidadeArtistas(0);
        setEndereco(endereco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Endereco getEndereco() {
        return endereco;
    }


    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    private void somaUMContadorEvento() {
        setContadorEvento(getContadorEvento() + 1);
    }

    public boolean isOpen() {
        return open;
    }

    public void fecharCandidatura() {
        setOpen(false);
    }

    public void abrirCandidatura() {
        if (!(this.data.isAfter(LocalDateTime.now()))) {
            setOpen(true);
        }
    }

    public void aumentarArtistas() {
        this.quantidadeArtistas++;
    }

    public int getCapacidadeEsperada() {
        return capacidadeEsperada;
    }

    public void setCapacidadeEsperada(int capacidadeEsperada) {
        if (capacidadeEsperada <= 0) {
            capacidadeEsperada = 1;
        }
        this.capacidadeEsperada = capacidadeEsperada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
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

    public void setContadorEvento(int cont) {
        Evento.contadorEvento = cont;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeArtistas() {
        return quantidadeArtistas;
    }

    public void setQuantidadeArtistas(int quantidadeArtistas) {
        this.quantidadeArtistas = quantidadeArtistas;
    }

    @Override
    public String toString() {
        return "Evento: " + this.id + "\nNome: " + this.nome + "\nEstilo: " + this.estilo
                + "\nValor: " + this.valor + "\nCapacidade Esperada: " + this.capacidadeEsperada
                + "\n\nEndereco:\n" + this.endereco;
    }

    @Override
    public boolean equals(Object obj) {
        return this.nome.equals(((Evento) obj).getNome());
    }
}
