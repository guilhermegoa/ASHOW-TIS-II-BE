package br.com.business;

import java.io.Serializable;

public class Notificacao implements Serializable {
    public static final String CONTRATANTE = "contratante";
    public static final String ARTISTA = "artista";
    private static int maxId;
    private int id;
    private Proposta proposta;
    private boolean visualizou;
    private String paraTipo;


    public Notificacao() {
    }

    public Notificacao(Proposta idProposta, String paraTipo) {
        setParaTipo(paraTipo);
        setProposta(idProposta);
        setVisualizou(false);
        maxId++;
        setId(maxId);
    }

    public static int getMaxId() {
        return maxId;
    }

    public static void setMaxId(int maxId) {
        Notificacao.maxId = maxId;
    }

    public String getParaTipo() {
        return paraTipo;
    }

    public void setParaTipo(String paraTipo) {
        this.paraTipo = paraTipo;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public void visualizar() {
        setVisualizou(true);
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
