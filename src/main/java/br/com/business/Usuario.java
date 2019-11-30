package br.com.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public abstract class Usuario implements Serializable {
    private static int maxID = 0;
    private String dataUriFoto;
    private String senha;
    private String email;
    private String nome;
    private int ID;
    private int qntAvaliacoes;
    private int somaNotas;
    private float mediaAvaliacao;
    private Endereco endereco;
    private List<Integer> eventos = new ArrayList<>();
    private Collection<Avaliacao> avaliacoes = new HashSet<Avaliacao>();

    public Usuario() {
    }

    public String getDataUriFoto() {
        return dataUriFoto;
    }

    public void setDataUriFoto(String dataUriFoto) {
        this.dataUriFoto = dataUriFoto;
    }

    public Usuario(String nome, String senha, String email, String dataUriFoto) {
        setDataUriFoto(dataUriFoto);
        setNome(nome);
        setSenha(senha);
        setEmail(email);
        maxID++;
        setID(maxID);
    }

    public static int getMaxID() {
        return maxID;
    }

    public static void setMaxID(int maxID) {
        if (Usuario.maxID < maxID) {
            Usuario.maxID = maxID;
        }
    }

    public boolean addEvento(Evento evento) {
        System.out.println("ADICIONANDO EVENTO AO CONTRATANTE: " + evento);
        return this.eventos.add(evento.getId());
    }

    public String getSenha() {
        return senha;
    }

//  public boolean addAvaliacao(Avaliacao a) {
//    for (Evento evento : eventos) {
//      if (evento == (a.getEvento())) {
//        qntAvaliacoes++;
//        somaNotas += a.getNotaFinal();
//        calculaMediaAvaliacao();
//        avaliacoes.add(a);
//        return true;
//      }
//    }
//    return false;
//  }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private float calculaMediaAvaliacao() {
        this.mediaAvaliacao = somaNotas / qntAvaliacoes;
        return mediaAvaliacao;
    }

    public List<Integer> getEventos() {
        return eventos;
    }

    public Collection<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

//  public StringBuilder verEventos() {
//    StringBuilder string = new StringBuilder();
//    for (Evento evento : eventos) {
//      string.append(evento).append("\n");
//    }
//    return string;
//  }

    @Override
    public String toString() {
        return "{\n\"id\": "
                + this.ID
                + ",\n\"nome\": \""
                + this.nome
                + "\",\n\"email\": \""
                + this.email
                + "\",\n\"media\": "
                + this.mediaAvaliacao
                + "\",\n\"senha\": "
                + this.getSenha()
                + "\",\n\"eventos\": "
                + this.getEventos();
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

    public boolean isID(int integer) {
        return getID() == integer;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isSenha(String senhaTeste) {
        return senhaTeste.equals(getSenha());
    }

    public boolean removeEvento(Evento evento) {
        return eventos.remove(((Integer) evento.getId()));
    }
}
