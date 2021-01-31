package br.com.business;

public class Pagamento {
  private static int pagamentoCancelados = 0;
  private String formaPagamento;
  private boolean cancelado;
  private Double valorAPagar;
  private Contrato contrato;

  private Pagamento() {
    formaPagamento = null;
    cancelado = false;
    valorAPagar = null;
  }

  public Pagamento(String formaPagamento, Contrato contrato) {
    this.formaPagamento = formaPagamento;
    this.valorAPagar = contrato.getValor();
    this.contrato = contrato;
  }

  public static int getPagamentoCancelados() {
    return pagamentoCancelados;
  }

  public static void setPagamentoCancelados(int pagamentoCancelados) {
    Pagamento.pagamentoCancelados = pagamentoCancelados;
  }

  public Contrato getContrato() {
    return contrato;
  }

  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }

  public String getFormaPagamento() {
    return formaPagamento;
  }

  public void setFormaPagamento(String formaPagamento) {
    this.formaPagamento = formaPagamento;
  }

  public boolean isCancelado() {
    return cancelado;
  }

  public void setCancelado(boolean cancelado) {
    this.cancelado = cancelado;
  }

  public double getValorAPagar() {
    return valorAPagar;
  }

  public void setValorAPagar(Double valorAPagar) {
    this.valorAPagar = valorAPagar;
  }

  public void setValorAPagar(double valorAPagar) {
    this.valorAPagar = valorAPagar;
  }

  public boolean foiPago() {
    return true;
  }

  public boolean cancelar() {
    return true;
  }
}
