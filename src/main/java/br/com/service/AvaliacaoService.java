package br.com.service;

import br.com.business.Avaliacao;
import br.com.repository.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("avaliacao")
public class AvaliacaoService {

  private Repository repository = Repository.getINSTANCE();

  @GET
  @Path("all")
  @Produces({MediaType.APPLICATION_JSON})
  public List<Avaliacao> getAll() {
    return repository.daoAvaliacao.getAll();
  }

  @GET
  @Path("{idevento}")
  @Produces({MediaType.APPLICATION_JSON})
  public List<Avaliacao> getAvaliacao(@PathParam("idevento") int id) {
    return repository.daoAvaliacao.getAll().stream()
        .filter(c -> c.getIdEvento() == id)
        .collect(Collectors.toList());
  }

  @GET
  @Path("{idevento}/{email}")
  @Produces({MediaType.APPLICATION_JSON})
  public List<Avaliacao> getAvaliacaoEmail(
      @PathParam("email") String email, @PathParam("idevento") int id) {
    return repository.daoAvaliacao.getAll().stream()
        .filter(o -> o.getEmail().equals(email))
        .filter(c -> c.getIdEvento() == id)
        .collect(Collectors.toList());
  }

  @POST
  @Path("add")
  @Consumes({MediaType.APPLICATION_JSON})
  public boolean contratanteAdd(Avaliacao avaliacao) {
    return repository.daoAvaliacao.add(avaliacao);
  }

  @DELETE
  @Path("delete/{idevento}/{email}")
  @Produces({MediaType.TEXT_PLAIN})
  public boolean remove(@PathParam("idevento") int id, @PathParam("email") String email) {
    System.out.println("DELETE Avaliacao:");
    List<Avaliacao> avaliacaoes =
        repository.daoAvaliacao.getAll().stream()
            .filter(v -> v.getIdEvento() == id)
            .filter(c -> c.getEmail().equals(email))
            .collect(Collectors.toList());
    return repository.daoAvaliacao.remove(avaliacaoes.get(0));
  }
}
