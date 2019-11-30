package br.com.business;

import java.io.Serializable;

public class Artista extends Usuario implements Serializable {
  private static int contadorArtistas;
  private String estilo;
  private String nomeArtistico;
  private String tipoArtista;
  private String contatoPublico;
  private String descricao;
  private String popularidade;
  private int numeroEventos;
  private float valorPadrao;

  public Artista() {}

  public Artista(
      String nome,
      String nomeArtistico,
      String senha,
      String email,
      String estilo,
      String tipoArtista,String dataUriFoto) {
    super(nome, senha, email,dataUriFoto);
    setNomeArtistico(nomeArtistico);
    setEstilo(estilo);
    setTipoArtista(tipoArtista);
    somaUMContadorArtista();
  }

  private void somaUMContadorArtista() {
    setContadorArtistas(getContadorArtistas() + 1);
  }

  public String calcularPopularidade(int eventos, float notas) {
    return "0";
  }

  @Override
  public boolean addEvento(Evento evento) {
    numeroEventos++;
    return super.addEvento(evento);
  }

  public boolean removerEvento(Evento evento) {
    return false;
  }

  @Override
  public String toString() {
    return super.toString()
        + ",\n\"nome_artistico\": "
        + getNomeArtistico()
        + ",\n\"tipo_de_artista\": "
        + getTipoArtista()
        + ",\n\"numero_de_eventos\": "
        + getNumeroEventos()
        + ",\n\"popularidade\": "
        + getPopularidade()
        + "\n}";
  }

  public static int getContadorArtistas() {
    return contadorArtistas;
  }

  public static void setContadorArtistas(int contadorArtistas) {
    Artista.contadorArtistas = contadorArtistas;
  }

  public String getEstilo() {
    return estilo;
  }

  public void setEstilo(String estilo) {
    this.estilo = estilo;
  }

  public String getNomeArtistico() {
    return nomeArtistico;
  }

  public void setNomeArtistico(String nomeArtistico) {
    this.nomeArtistico = nomeArtistico;
  }

  public String getTipoArtista() {
    return tipoArtista;
  }

  public void setTipoArtista(String tipoArtista) {
    this.tipoArtista = tipoArtista;
  }

  public String getContatoPublico() {
    return contatoPublico;
  }

  public void setContatoPublico(String contatoPublico) {
    this.contatoPublico = contatoPublico;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getPopularidade() {
    return popularidade;
  }

  public void setPopularidade(String popularidade) {
    this.popularidade = popularidade;
  }

  public int getNumeroEventos() {
    return numeroEventos;
  }

  public void setNumeroEventos(int numeroEventos) {
    this.numeroEventos = numeroEventos;
  }

  public float getValorPadrao() {
    return valorPadrao;
  }

  public void setValorPadrao(float valorPadrao) {
    this.valorPadrao = valorPadrao;
  }
}
