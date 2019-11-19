package br.com.business;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Evento implements Serializable {
    private static int maxIDEventos = 0;
    private int id;
    private int capacidadeEsperada;
    private int quantidadeArtistas;
    private double valor;
    private String estilo;
    private String nome;
    private boolean open;
    private LocalDateTime data;
    private Endereco endereco;
    private Contratante contratante;

    public Evento() {
    }

    public Evento(
            String nome,
            int capacidadeEsperada,
            double valor,
            String estilo,
            LocalDateTime data,
            Endereco endereco,
            int quantidadeArtistas) {
        setQuantidadeArtistas(quantidadeArtistas);
        setNome(nome);
        setCapacidadeEsperada(capacidadeEsperada);
        setValor(valor);
        setEstilo(estilo);
        setData(data);
        setEndereco(endereco);
        maxIDEventos++;
        setId(getMaxIDEventos());
        setOpen(true);
    }

    public static int getMaxIDEventos() {
        return maxIDEventos;
    }

    public static void setMaxIDEventos(int maxIDEventos) {
        Evento.maxIDEventos = maxIDEventos;
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    public void setMaxIDEvento(int cont) {
        Evento.maxIDEventos = cont;
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
        return "Evento: "
                + this.id
                + "\nNome: "
                + this.nome
                + "\nEstilo: "
                + this.estilo
                + "\nValor: "
                + this.valor
                + "\nCapacidade Esperada: "
                + this.capacidadeEsperada
                + "\nEndereco:\n"
                + this.endereco
                + "\nQuantidade artistas:"
                + this.quantidadeArtistas;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Evento) return this.id == ((Evento) obj).id;
        else return false;
    }
}
