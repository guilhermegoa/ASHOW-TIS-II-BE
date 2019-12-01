package br.com.service;

import br.com.business.Contratante;
import br.com.business.Evento;
import br.com.repository.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("evento")
public class EventoService {
    private Repository repository = Repository.getINSTANCE();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Evento> getAll() {
        return repository.daoEventos.getAll();
    }

    @GET
    @Path("filter/{style}/{local}/{min}/{max}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Evento> filter(@PathParam("style") String estilo, @PathParam("local") String local, @PathParam("min") double min, @PathParam("max") double max){
        List<Evento> filtrado = repository.daoEventos.getAll();
        List<Evento> aux = new ArrayList<Evento>();
        if(!(estilo.equals("@")) &&!(local.equals("@"))){
            filtrado.stream().filter(e -> e.getEstilo().toLowerCase().contains(estilo.toLowerCase()))
                    .filter(e -> e.getEndereco().getCidade().toLowerCase().contains(local.toLowerCase()))
                    .filter(e -> e.getValor() >= min && e.getValor() <= max)
                    .forEach(e -> aux.add(e));
            filtrado = aux;
        }
        else if(!(local.equals("@"))){
            filtrado.stream().filter(e -> e.getEndereco().getCidade().toLowerCase().contains(local.toLowerCase()))
                    .filter(e -> e.getValor() >= min && e.getValor() <= max)
                    .forEach(e -> aux.add(e));
            filtrado = aux;
        }
        else if(!(estilo.equals("@"))){
            filtrado.stream().filter(e -> e.getEstilo().toLowerCase().contains(estilo.toLowerCase()))
                    .filter(e -> e.getValor() > min && e.getValor() <= max)
                    .forEach(e -> aux.add(e));
            filtrado = aux;
        }
        else{
            filtrado.stream()
                    .filter(e -> e.getValor() > min && e.getValor() <= max)
                    .forEach(e -> aux.add(e));
            filtrado = aux;
        }
       return filtrado;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Evento get(@PathParam("id") int id) {
        return repository.daoEventos != null
                ? repository.daoEventos.get(id)
                : null;
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean add(Evento evento) {
        System.out.println("ADD Evento: " + evento.getId());
        System.out.println("evento.getEmailContratante() = " + evento.getEmailContratante());
        Contratante contratante = repository.daoContratantes.get(evento.getEmailContratante());
        if (contratante != null) {
            Evento eventonew =
                    new Evento(
                            evento.getNome(),
                            evento.getCapacidadeEsperada(),
                            evento.getValor(),
                            evento.getEstilo(),
                            evento.getDataEvento(),
                            evento.getEndereco(),
                            evento.getQuantidadeArtistas(),
                            evento.getEmailContratante(),
                            evento.getDataUriFoto());
            boolean a = repository.daoContratantes.addEvento(eventonew, evento.getEmailContratante());
            boolean b = repository.daoEventos.add(eventonew);
            System.out.println(a);
            System.out.println(b);
            return a && b;
        } else return false;
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean update(Evento evento) {
        System.out.println(evento);
        int id = evento.getId();
        boolean a = repository.daoEventos.update(id, evento);
        System.out.println(a);
        return a;
    }

    @DELETE
    @Path("delete/{id}")
    public boolean remove(@PathParam("id") int id) {
        System.out.println("DELETE Evento:");
        List<Evento> eventos =
                repository.daoEventos.getAll().stream()
                        .filter(a -> a.getId() == (id))
                        .collect(Collectors.toList());
        if (!eventos.isEmpty()) {
            Evento evento = (eventos.get(0));
            System.out.println(evento);
            if (evento != null) {
                boolean a = repository.daoEventos.remove(evento);
                boolean b = repository.daoContratantes.get(evento.getEmailContratante()).removeEvento(evento);
                System.out.println(a);
                System.out.println(b);
                return a && b;
            } else return false;
        } else return false;
    }

    @GET
    @Path("{id}/add/artista/pendente/{email}")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean addArtistaPendente(@PathParam("id") int id, @PathParam("email") String email) {
        if (repository.daoEventos.get(id).isOpen()) {
            boolean a = repository.daoEventos.addArtistaPendente(id, email);
            boolean b = repository.daoArtistas.addEvento(repository.daoEventos.get(id), email);
            return a && b;
        } else return false;
    }

    @DELETE
    @Path("{id}/delete/artista/pendente/{email}")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean deleteArtistaPendente(@PathParam("id") int id, @PathParam("email") String email) {
        boolean a = repository.daoEventos.removerArtistaPendente(id, email);
        boolean b = repository.daoArtistas.removerEvento(repository.daoEventos.get(id), email);
        return a && b;
    }

    @DELETE
    @Path("{id}/delete/artista/confirmado/{email}")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean deleteArtistaConfirmado(@PathParam("id") int id, @PathParam("email") String email) {
        boolean a = repository.daoEventos.removerArtistaConfirmado(id, email);
        boolean b = repository.daoArtistas.removerEvento(repository.daoEventos.get(id), email);
        return a && b;
    }

    @PUT
    @Path("{id}/comfirmar/artista/pendente/{email}")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean confirmarArtistaPendente(@PathParam("id") int id, @PathParam("email") String email) {
        boolean a = repository.daoEventos.confirmarArtistaPendente(id, email);
        return a;
    }

    @PUT
    @Path("{id}/fechar")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean fecharEvento(@PathParam("id") int id) {
        boolean a = repository.daoEventos.fecharEvento(id);
        return a;
    }

    @PUT
    @Path("{id}/abrir")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean abrirEvento(@PathParam("id") int id) {
        boolean a = repository.daoEventos.abrirEvento(id);
        return a;
    }

}
