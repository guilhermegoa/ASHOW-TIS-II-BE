package Ashow.repository;

import Ashow.business.Contratante;
import Ashow.business.Usuario;
import Ashow.dao.Dao;
import Ashow.interfac.IRepository;

import java.io.IOException;
import java.io.Serializable;

public class RepositoryContratante implements IRepository, Serializable {

    private static final String FILE = "src/main/java/Ashow/bin/contratante.bin";
    public final Dao<Contratante, Integer> CONTRATANTE_DAO = inicializarDao();

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
