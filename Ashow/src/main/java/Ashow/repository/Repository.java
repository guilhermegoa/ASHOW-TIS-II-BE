package Ashow.repository;

import Ashow.business.Artista;
import Ashow.business.Contratante;
import Ashow.business.Usuario;
import Ashow.dao.Dao;
import Ashow.dao.DaoUsuario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Repository implements Serializable {

    private static final String FILE = "src/main/java/Ashow/bin/usuario.bin";
    private static final String FILEARTISTAS = "src/main/java/Ashow/bin/artista.bin";
    private static final String FILECONTRATANTE = "src/main/java/Ashow/bin/contratante.bin";
    public final Dao<Usuario, Integer> dao_usuarios = inicializarDao(FILE);
    public final Dao<Artista, Integer> dao_artistas = inicializarDao(FILEARTISTAS);
    public final Dao<Contratante, Integer> dao_contratantes = inicializarDao(FILECONTRATANTE);

    public Dao inicializarDao(String arqv) {
        try {
            Dao dao = new DaoUsuario(arqv);
            return dao;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
