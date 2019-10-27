package Ashow.business;

import java.io.Serializable;

public class Contratante extends Usuario implements Serializable {
    private static int contadorContratantes;
    private int numeroEventos;

    public Contratante(String nome, String senha, String email) {
        super(nome, senha, email);
    }


    public void criarEvento(Evento evento) {
        eventos.add(evento);
        numeroEventos++;
    }

    public void fecharEvento(Evento evento) {

    }

    @Override
    public String toString() {
        return super.toString() + "\nN�mero de eventos: " + this.numeroEventos;
    }

}
