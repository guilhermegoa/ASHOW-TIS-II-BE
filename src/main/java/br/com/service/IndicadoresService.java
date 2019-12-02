package br.com.service;

import br.com.business.Artista;
import br.com.business.Evento;
import br.com.repository.Repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("indicador")
public class IndicadoresService {
    private Repository repository = Repository.getINSTANCE();
    int contA = repository.daoArtistas.getAll().size();
    int contC = repository.daoContratantes.getAll().size();
    List<Double> lista = new ArrayList<Double>();
    List<Artista> artistas = repository.daoArtistas.getAll();
    List<Evento> eventos = repository.daoEventos.getAll();
    double contV = calculaValores();
    double contVD = calculaValoresEvento();

    public double calculaValoresEvento() {
        for (Evento a : eventos) {
            lista.add((double) a.getValor());
        }
        double result = 0;
        for (Double d : lista) {
            result += d;
        }
        lista.clear();
        return result;
    }

    public double calculaValores() {
        for (Artista a : artistas) {
            lista.add((double) a.getValorPadrao());
        }
        double result = 0;
        for (Double d : lista) {
            result += d;
        }
        lista.clear();
        return result;
    }

    Armazem armazem = new Armazem(contA, contC, contV, contVD, eventos.size());

    @GET
    @Path("get")
    @Produces({MediaType.APPLICATION_JSON})
    public Armazem get(){
        return armazem;
    }

}
