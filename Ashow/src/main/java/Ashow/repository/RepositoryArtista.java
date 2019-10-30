package Ashow.repository;

import Ashow.business.Artista;
import Ashow.business.Usuario;
import Ashow.dao.Dao;
import Ashow.interfac.IRepository;
import java.io.IOException;
import java.io.Serializable;

public class RepositoryArtista implements IRepository, Serializable {

    private static final String FILE = "src/main/java/Ashow/bin/artista.bin";
    public final Dao<Artista, Integer> ARTISTA_DAO = inicializarDao();

    @Override
    public Dao inicializarDao() {
        try {
            return new Dao(FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
