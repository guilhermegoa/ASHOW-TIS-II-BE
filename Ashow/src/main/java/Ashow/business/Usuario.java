package Ashow.business;

import Ashow.interfac.UtilitarioDoDao;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalInt;

public abstract class Usuario implements Serializable {
    private static int maxID = 0;
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
        setSenha(senha);
        setEmail(email);
        maxID++;
        setID(maxID);
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
        return "{\n\"id\": " + this.ID + ",\n\"nome\": \"" + this.nome + "\",\n\"email\": \"" + this.email +
                "\",\n\"media\": " + this.mediaAvaliacao + "\",\n\"senha\": " + this.getSenha();
    }

    public StringBuilder verEventos() {
        StringBuilder string = new StringBuilder();
        for (Evento evento : eventos) {
            string.append(evento).append("\n");
        }
        return string;
    }

    public StringBuilder verAvaliacoes() {
        StringBuilder string = new StringBuilder();
        for (Avaliacao a : avaliacoes) {
            string.append(a).append("\n");
        }
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        return this.email.equals(((Usuario) obj).getEmail());
    }

    public static int getMaxID() {
        return maxID;
    }

    public static void setMaxID(int maxID) {
        if (Usuario.maxID < maxID) {
            Usuario.maxID = maxID;
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

    public boolean isID(Integer integer) {
        return getID() == integer;
    }

    public Integer getID() {
        return ID;
    }
}
