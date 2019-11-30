package br.com.business;

import java.io.Serializable;

public class Notificacao implements Serializable {
    private static int maxId;
    private int id;
    private int idProposta;
    private boolean visualizou;

    public Notificacao() {
    }

    public int getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(int idProposta) {
        this.idProposta = idProposta;
    }

    public Notificacao(int idProposta) {
        setIdProposta(idProposta);
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
            return notificacao.idProposta == this.idProposta;
        } else return false;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

}
