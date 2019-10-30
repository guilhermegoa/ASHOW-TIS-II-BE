package Ashow.business;

import Ashow.interfac.UtilitarioDoDao;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalInt;

public abstract class Usuario implements Serializable, UtilitarioDoDao<Integer> {
    private static int contador = 0;
    private String senha;
    private String email;
    private String nome;
    private int ID;
    private int qntAvaliacoes;
    private int somaNotas;
    private float mediaAvaliacao;
    private Endereco endereco;
    private Collection<Evento> eventos = new HashSet<Evento>();
    private Collection<Avaliacao> avaliacoes = new HashSet<Avaliacao>();

    public Usuario() {

    }

    public String getSenha() {
        return senha;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String nome, String senha, String email) {
        setNome(nome);
        this.senha = senha;
        setEmail(email);
        somaUmContadorUsuario();
        this.ID = contador;
        contador++;
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

    public Collection<Evento> getEventos() {
        return eventos;
    }

    public Collection<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    @Override
    public String toString() {
        return "{\n\"usuario\": " + this.ID + ",\n\"nome\": \"" + this.nome + "\",\n\"email\": \"" + this.email +
                "\",\n\"media\": " + this.mediaAvaliacao + "\",\nsenha: " + this.getSenha();
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

    public static void setContador(OptionalInt contador) {
        if (Usuario.contador < contador.getAsInt()) {
            Usuario.contador = contador.getAsInt();
        }
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

    @Override
    public boolean isID(Integer integer) {
        return getID() == integer;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    public static void setContador(int contador) {
        Usuario.contador = contador;
    }
}
