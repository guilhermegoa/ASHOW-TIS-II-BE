package br.com.business;

import java.io.Serializable;

public class Avaliacao implements Serializable {

  private static int maxId;
  private static int contadorAvaliacoes = 0;
  private String comentario;
  private int notaFinal;
  private int idEvento;
  String email;



  public Avaliacao() {}

  public Avaliacao(
          String comentario,
          int notaFinal,
          int idevento,
          String email) {
    this.setComentario(comentario);
    setIdEvento(idevento);
    setEmail(email);
    this.setNotaFinal(notaFinal);
    contadorAvaliacoes++;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public int getNotaFinal() {
    return notaFinal;
  }

  public void setNotaFinal(int notaFinal) {
    this.notaFinal = notaFinal;
  }

  public int getIdEvento() {
    return idEvento;
  }

  public void setIdEvento(int idEvento) {
    this.idEvento = idEvento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public static long getContadorAvaliacoes() {
    return contadorAvaliacoes;
  }

  public static void setContadorAvaliacoes(int contadorAvaliacoes) {
    Avaliacao.contadorAvaliacoes = contadorAvaliacoes;
  }

  public static void setMaxId(int maxId) {
    Avaliacao.maxId = maxId;
  }

  @Override
  public String toString() {
    return "\"" + this.comentario + "\"" + "\nNota: " + this.notaFinal;
  }
}
