package br.com.business;

import java.io.Serializable;

public class Proposta implements Serializable {
    private static int maxId;
    private int id;
    private boolean artistaAceitou;
    private boolean contratanteAceitou;
    private String emailArtista;
    private String emailContratante;
    private int idEvento;
    private double valor;

    public static int getMaxId() {
        return maxId;
    }

    public Proposta() {
    }

    public Proposta(String artista, String contratante, int evento, double valor) {
        setEmailArtista(artista);
        setIdEvento(evento);
        setEmailContratante(contratante);
        setValor(valor);
        setArtistaAceitou(false);
        setContratanteAceitou(false);
        maxId++;
        setId(maxId);
    }

    public static void setMaxId(int maxId) {
        Proposta.maxId = maxId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Proposta) {
            Proposta proposta = ((Proposta) obj);
            return proposta.getIdEvento() == this.idEvento && proposta.emailArtista.equals(emailArtista) && proposta.emailContratante.equals(emailContratante);
        } else return false;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getEmailArtista() {
        return emailArtista;
    }

    public void setEmailArtista(String emailArtista) {
        this.emailArtista = emailArtista;
    }

    public String getEmailContratante() {
        return emailContratante;
    }

    public void setEmailContratante(String emailContratante) {
        this.emailContratante = emailContratante;
    }

    public boolean isArtistaAceitou() {
        return artistaAceitou;
    }

    public void setArtistaAceitou(boolean artistaAceitou) {
        this.artistaAceitou = artistaAceitou;
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

    public void setContratanteAceitou(boolean contratanteAceitou) {
        this.contratanteAceitou = contratanteAceitou;
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
