package Ashow.business;

import java.io.Serializable;

public class Contratante extends Usuario implements Serializable {
    private static int contadorContratantes = 0;
    private int numeroEventos = 0;

    public Contratante(){}

    public Contratante(String nome, String senha, String email) {
        super(nome, senha, email);
    }


    public void criarEvento(Evento evento) {
        this.getEventos().add(evento);
        numeroEventos++;
    }

    public void fecharEvento(Evento evento) {

    }

    @Override
    public String toString() {
        return super.toString() + ",\n\"N�mero de eventos\": " + this.numeroEventos + "\n}";
    }

}
