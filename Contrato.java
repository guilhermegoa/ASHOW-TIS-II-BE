public class Contrato {
    private static int contadorContratos = 0;
    private Double valor;
    private boolean validado;
    private String descricao;
    private Pagamento pagamento;
    private Artista artista;
    private Contratante contratante;
    private Proposta proposta;
    private Evento evento;

    public static int getContadorContratos() {
        return contadorContratos;
    }

    public static void setContadorContratos(int contadorContratos) {
        Contrato.contadorContratos = contadorContratos;
    }

    public boolean validarContrato() {
        return false;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
