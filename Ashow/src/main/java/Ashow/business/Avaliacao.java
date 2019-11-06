package Ashow.business;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    private static long contadorAvaliacoes;
    private String comentario;
    private int notaFinal;
    private Evento evento;

    public Avaliacao(String comentario, int notaFinal, Evento evento) {
        this.setComentario(comentario);
        this.setEvento(evento);
        this.setNotaFinal(notaFinal);
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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public static long getContadorAvaliacoes() {
        return contadorAvaliacoes;
    }

    @Override
    public String toString() {
        return "\"" + this.comentario + "\"" + "\nNota: " + this.notaFinal;
    }

}
