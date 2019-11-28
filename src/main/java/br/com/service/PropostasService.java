package br.com.service;

import br.com.business.Artista;
import br.com.business.Contratante;
import br.com.business.Evento;
import br.com.business.Proposta;
import br.com.repository.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Path("proposta")
public class PropostasService {
    private Repository repository = Repository.getINSTANCE();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Proposta> getAll() {
        return repository.daoPropostas.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Proposta get(@PathParam("id") int id) {
        return repository.daoPropostas != null
                ? repository.daoPropostas.get(id)
                : null;
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean add(Proposta proposta) {
        System.out.println("ADD proposta: " + proposta.getId());
        Contratante contratante = repository.daoContratantes.get(proposta.getEmailContratante());
        Artista artista = repository.daoArtistas.get(proposta.getEmailArtista());
        Evento evento = repository.daoEventos.get(proposta.getIdEvento());
        if (contratante != null && artista != null && evento != null) {
            Proposta propostanew =
                    new Proposta(proposta.getEmailArtista(), proposta.getEmailContratante(), proposta.getIdEvento(), proposta.getValor());
            boolean a = repository.daoPropostas.add(propostanew);
            System.out.println(a);
            return a;
        } else return false;
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean update(Proposta proposta) {
        System.out.println(proposta);
        int id = proposta.getId();
        boolean a = repository.daoPropostas.update(id, proposta);
        System.out.println(a);
        return a;
    }

    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean remove(@PathParam("id") int id) {
        System.out.println("DELETE Proposta:");
        List<Proposta> propostas =
                repository.daoPropostas.getAll().stream()
                        .filter(a -> a.getId() == (id))
                        .collect(Collectors.toList());
        if (!propostas.isEmpty()) {
            Proposta proposta = (propostas.get(0));
            System.out.println(proposta);
            if (proposta != null) {
                boolean a = repository.daoPropostas.remove(proposta);
                System.out.println(a);
                return a;
            } else return false;
        } else return false;
    }

    @GET
    @Path("attall")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean attall() {
        repository.daoPropostas.getAll().stream().forEach(new Consumer<Proposta>() {
            @Override
            public void accept(Proposta proposta) {
                final boolean[] achou = {false};
                if (proposta.isArtistaAceitou() && proposta.isContratanteAceitou()) {
                    repository.daoEventos.get(proposta.getIdEvento()).getEmailArtistasPendente().forEach(new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            if (!achou[0]) {
                                achou[0] = s.equals(proposta.getEmailArtista());
                            }
                        }
                    });
                    if (!achou[0])
                        repository.daoEventos.addArtistaPendente(proposta.getIdEvento(),proposta.getEmailArtista());
                }
            }
        });
        return false;
    }

}
