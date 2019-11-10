package Ashow.business;

public class Proposta {
  private boolean artistaAceitou;
  private boolean contratanteAceitou;
  private double valor;

  public Proposta() {
    artistaAceitou = false;
    contratanteAceitou = false;
  }

  public boolean isArtistaAceitou() {
    return artistaAceitou;
  }

  public void artistaAceita() {
    artistaAceitou = true;
  }

  public void artistaRecusa() {
    artistaAceitou = false;
  }

  public boolean isContratanteAceitou() {
    return contratanteAceitou;
  }

  public void contratanteAceita() {
    contratanteAceitou = true;
  }

  public void contratanteRecusa() {
    contratanteAceitou = false;
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
}
