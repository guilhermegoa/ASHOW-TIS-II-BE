package br.com.service;

import br.com.business.Evento;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("evento")
public class EventoService {

  @GET
  @Path("all")
  @Produces({MediaType.APPLICATION_JSON})
  public List<Evento> getAll() {
    return Sistema.getRepository().daoEventos.getAll();
  }

  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public Evento get(@PathParam("id") int id) {
    return Sistema.getRepository().daoEventos != null
        ? Sistema.getRepository().daoEventos.get(id)
        : null;
  }

  @POST
  @Path("add")
  @Consumes({MediaType.APPLICATION_JSON})
  public boolean add(Evento evento) {
    System.out.println("ADD Evento: " + evento.getQuantidadeArtistas());
    Evento eventonew =
        new Evento(
            evento.getNome(),
            evento.getCapacidadeEsperada(),
            evento.getValor(),
            evento.getEstilo(),
            evento.getData(),
            evento.getEndereco(),
            evento.getQuantidadeArtistas());
    System.out.println(eventonew);
    boolean a = Sistema.getRepository().daoEventos.add(eventonew);
    System.out.println(a);
    return a;
  }

  @PUT
  @Path("update")
  @Consumes({MediaType.APPLICATION_JSON})
  public boolean update(Evento evento) {
    System.out.println(evento);
    int id = evento.getId();
    boolean a = Sistema.getRepository().daoEventos.update(id, evento);
    System.out.println(a);
    return a;
  }

  @DELETE
  @Path("delete/{id}")
  public boolean remove(@PathParam("id") int id) {
    System.out.println("DELETE Evento:");
    List<Evento> eventos =
        Sistema.getRepository().daoEventos.getAll().stream()
            .filter(a -> a.getId() == (id))
            .collect(Collectors.toList());
    if (!eventos.isEmpty()) {
      Evento evento = (eventos.get(0));
      System.out.println(evento);
      if (evento != null) {
        boolean a = Sistema.getRepository().daoEventos.remove(evento);
        System.out.println(a);
        return a;
      } else return false;
    } else return false;
  }
}
