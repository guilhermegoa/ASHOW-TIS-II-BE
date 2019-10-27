package Ashow.business;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public abstract class Usuario implements Serializable {
    private static int contador = 0;
    private String senha;
    private String email;
    private String nome;
    private int ID;
    private int qntAvaliacoes;
    private int somaNotas;
    private float mediaAvaliacao;
    private Endereco endereco;
    Collection<Evento> eventos = new HashSet<Evento>();
    Collection<Avaliacao> avaliacoes = new HashSet<Avaliacao>();

    public Usuario(String nome, String senha, String email) {
        setNome(nome);
        this.senha = senha;
        setEmail(email);
        somaUmContadorUsuario();
        this.ID = contador;
    }

    private void somaUmContadorUsuario() {
        setContador(getContador() + 1);
    }

    public boolean addAvaliacao(Avaliacao a) {
        for (Evento evento : eventos) {
            if (evento.equals(a.getEvento())) {
                qntAvaliacoes++;
                somaNotas += a.getNotaFinal();
                calculaMediaAvaliacao();
                avaliacoes.add(a);
                return true;
            }
        }
        return false;
    }

    public float calculaMediaAvaliacao() {
        this.mediaAvaliacao = somaNotas / qntAvaliacoes;
        return mediaAvaliacao;
    }


    @Override
    public String toString() {
        return "\nUsu�rio: " + this.ID + "\nNome: " + this.nome + "\nE-mail: " + this.email +
                "\nM�dia: " + this.mediaAvaliacao;
    }

    public StringBuilder verEventos() {
        StringBuilder string = new StringBuilder();
        for (Evento evento : eventos) {
            string.append(evento + "\n");
        }
        return string;
    }

    public StringBuilder verAvaliacoes() {
        StringBuilder string = new StringBuilder();
        for (Avaliacao a : avaliacoes) {
            string.append(a + "\n");
        }
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        return this.email.equals(((Usuario) obj).getEmail());
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Usuario.contador = contador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public float getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(float mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
