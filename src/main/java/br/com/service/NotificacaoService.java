package br.com.service;

import br.com.business.Notificacao;
import br.com.repository.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("notificacao")
public class NotificacaoService {
    private Repository repository = Repository.getINSTANCE();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Notificacao> getAll() {
        return repository.daoNotificacao.getAll();
    }

    @GET
    @Path("contratante/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Notificacao> getNotContratante(@PathParam("email") String email) {
        return repository.daoNotificacao.getAll().stream().filter(o -> repository.daoPropostas.get(o.getProposta().getId()).getEmailContratante().equals(email)).collect(Collectors.toList());
    }

    @GET
    @Path("artista/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Notificacao> getNotArtista(@PathParam("email") String email) {
        return repository.daoNotificacao.getAll().stream().filter(o -> repository.daoPropostas.get(o.getProposta().getId()).getEmailArtista().equals(email)).collect(Collectors.toList());
    }

    @GET
    @Path("evento/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Notificacao> getNotEvento(@PathParam("id") int id) {
        return repository.daoNotificacao.getAll().stream().filter(o -> repository.daoPropostas.get(o.getProposta().getId()).getIdEvento() == id).collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Notificacao get(@PathParam("id") int id) {
        return repository.daoNotificacao.get(id);
    }

    @POST
    @Path("contratante/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean add(Notificacao notificacao) {
        Notificacao not = new Notificacao(notificacao.getProposta());
        return repository.daoNotificacao.add(not);
    }

//    @POST
//    @Path("contratante/add")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public boolean contratanteAdd(Notificacao proposta) {
//        Contratante contratante = repository.daoContratantes.get(proposta.getEmailContratante());
//        Artista artista = repository.daoArtistas.get(proposta.getEmailArtista());
//        Evento evento = repository.daoEventos.get(proposta.getIdEvento());
//        System.out.println(contratante != null && artista != null && evento != null && evento.getEmailContratante().equals(proposta.getEmailContratante()));
//        if (contratante != null && artista != null && evento != null && evento.getEmailContratante().equals(proposta.getEmailContratante())) {
//            Notificacao propostanew =
//                    new Notificacao(proposta.getEmailArtista(), proposta.getEmailContratante(), proposta.getIdEvento(), proposta.getValor());
//            propostanew.contratanteAceita();
//            boolean a = repository.daoPropostas.add(propostanew);
//            System.out.println(a);
//            if (!a) {
//                Notificacao.setMaxId(propostanew.getId() - 1);
//                final Notificacao[] propostaComp = new Notificacao[1];
//                repository.daoPropostas.getAll().forEach(o -> {
//                    if (o.equals(propostanew)) {
//                        propostaComp[0] = o;
//                    }
//                });
//                if (propostaComp[0].isArtistaAceitou()) {
//                    propostaComp[0].contratanteAceita();
//                    return repository.daoEventos.addArtistaPendente(proposta.getIdEvento(), proposta.getEmailArtista()) &&
//                            repository.daoArtistas.addEvento(repository.daoEventos.get(proposta.getIdEvento()), proposta.getEmailArtista()) &&
//                            repository.daoPropostas.saveInFile();
//                }
//                return false;
//            }
//            return true;
//        } else return false;
//    }
//
//    @POST
//    @Path("artista/add")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public boolean artistaAdd(Notificacao proposta) {
//        Contratante contratante = repository.daoContratantes.get(proposta.getEmailContratante());
//        Artista artista = repository.daoArtistas.get(proposta.getEmailArtista());
//        Evento evento = repository.daoEventos.get(proposta.getIdEvento());
//        System.out.println(contratante != null && artista != null && evento != null && evento.getEmailContratante().equals(proposta.getEmailContratante()));
//        if (contratante != null && artista != null && evento != null && evento.getEmailContratante().equals(proposta.getEmailContratante())) {
//            Notificacao propostanew = new Notificacao(proposta.getEmailArtista(), proposta.getEmailContratante(), proposta.getIdEvento(), proposta.getValor());
//            propostanew.artistaAceita();
//            boolean a = repository.daoPropostas.add(propostanew);
//            if (!a) {
//                final Notificacao[] propostaComp = new Notificacao[1];
//                repository.daoPropostas.getAll().forEach(o -> {
//                    if (o.equals(propostanew)) {
//                        propostaComp[0] = o;
//                    }
//                });
//                if (propostaComp[0].isContratanteAceitou() && !propostaComp[0].isArtistaAceitou()) {
//                    propostaComp[0].artistaAceita();
//                    return repository.daoEventos.addArtistaPendente(proposta.getIdEvento(), proposta.getEmailArtista()) &&
//                            repository.daoArtistas.addEvento(repository.daoEventos.get(proposta.getIdEvento()), proposta.getEmailArtista()) &&
//                            repository.daoPropostas.saveInFile();
//                }
//                return false;
//            } else return true;
//        } else return false;
//    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean update(Notificacao notificacao) {
        System.out.println(notificacao);
        int id = notificacao.getId();
        boolean a = repository.daoNotificacao.update(id, notificacao);
        System.out.println(a);
        return a;
    }

    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean remove(@PathParam("id") int id) {
        System.out.println("DELETE Notificacao:");
        List<Notificacao> propostas =
                repository.daoNotificacao.getAll().stream()
                        .filter(a -> a.getId() == (id))
                        .collect(Collectors.toList());
        if (!propostas.isEmpty()) {
            Notificacao notificacao = (propostas.get(0));
            System.out.println(notificacao);
            if (notificacao != null) {
                boolean a = repository.daoNotificacao.remove(notificacao);
                return a;
            } else return false;
        } else return false;
    }
}
