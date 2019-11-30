package br.com.business;

import java.io.Serializable;

public class Notificacao implements Serializable {
    private static int maxId;
    private int id;
    private Proposta proposta;
    private boolean visualizou;

    public Notificacao() {
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Notificacao(Proposta idProposta) {
        setProposta(idProposta);
        setVisualizou(false);
        maxId++;
        setId(maxId);
    }

    public void visualizar() {
        setVisualizou(true);
    }

    public static int getMaxId() {
        return maxId;
    }

    public static void setMaxId(int maxId) {
        Notificacao.maxId = maxId;
    }

    public boolean isVisualizou() {
        return visualizou;
    }

    public void setVisualizou(boolean visualizou) {
        this.visualizou = visualizou;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Notificacao) {
            Notificacao notificacao = ((Notificacao) obj);
            return notificacao.proposta == this.proposta;
        } else return false;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

}
