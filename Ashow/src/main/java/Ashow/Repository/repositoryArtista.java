package Ashow.Repository;

import Ashow.business.Artista;
import Ashow.business.Usuario;
import Ashow.dao.Dao;
import Ashow.interfac.IDao;
import Ashow.interfac.IRepository;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.IOException;
import java.util.List;

public class repositoryArtista implements IRepository {

    private static final String FILE = "bin/artista";
    public final IDao<Artista, String> USUARIO_DAO = initDao();

    @Override
    public Dao initDao() {
        try {
            return new Dao(FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getList() {
        return USUARIO_DAO.getAll();
    }

    public Response add(Artista artista) {
        if (USUARIO_DAO.add(artista))
            return Response.status(Status.OK).build();
        else
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();

    }

    public Response update(DogWalker dogWalker) {
        try {
            validarRequisicao(dogWalker);
            if (DOGWALKER_DAO.update(dogWalker))
                return Response.status(Status.OK).build();
            else
                return Response.status(Status.NOT_FOUND).build();
        } catch (ValorNegativoException | StringVaziaException
                | MenorIdadeException | AgendaNullException | EmailInvalidoException |
                NullPointerException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    public Response remove(String id) {
        if (id != null) {
            if (!(id.isEmpty())) {
                if (DOGWALKER_DAO.remove(id))
                    return Response.status(Status.OK).build();
                else
                    return Response.status(Status.NOT_FOUND).build();
            }
        }
        return Response.status(Status.BAD_REQUEST).build();

    }
}
