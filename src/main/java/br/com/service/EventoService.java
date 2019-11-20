package br.com.service;

import br.com.business.Contratante;
import br.com.business.Evento;
import br.com.repository.Repository;

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
        return Repository.getINSTANCE().daoEventos.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Evento get(@PathParam("id") int id) {
        return Repository.getINSTANCE().daoEventos != null
                ? Repository.getINSTANCE().daoEventos.get(id)
                : null;
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean add(Evento evento) {
        System.out.println("ADD Evento: " + evento.getId());
        System.out.println("evento.getEmailContratante() = " + evento.getEmailContratante());
        Contratante contratante = Repository.getINSTANCE().daoContratantes.get(evento.getEmailContratante());
        if (contratante != null) {
            Evento eventonew =
                    new Evento(
                            evento.getNome(),
                            evento.getCapacidadeEsperada(),
                            evento.getValor(),
                            evento.getEstilo(),
                            evento.getData(),
                            evento.getEndereco(),
                            evento.getQuantidadeArtistas(),
                            evento.getEmailContratante());
            boolean a =contratante.addEvento(eventonew);
            boolean b = Repository.getINSTANCE().daoEventos.add(eventonew);
            System.out.println(a);
            System.out.println(b);
            return a&&b;
        } else return false;
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean update(Evento evento) {
        System.out.println(evento);
        int id = evento.getId();
        boolean a = Repository.getINSTANCE().daoEventos.update(id, evento);
        System.out.println(a);
        return a;
    }

    @DELETE
    @Path("delete/{id}")
    public boolean remove(@PathParam("id") int id) {
        System.out.println("DELETE Evento:");
        List<Evento> eventos =
                Repository.getINSTANCE().daoEventos.getAll().stream()
                        .filter(a -> a.getId() == (id))
                        .collect(Collectors.toList());
        if (!eventos.isEmpty()) {
            Evento evento = (eventos.get(0));
            System.out.println(evento);
            if (evento != null) {
                boolean a = Repository.getINSTANCE().daoEventos.remove(evento);
                System.out.println(a);
                return a;
            } else return false;
        } else return false;
    }
}
