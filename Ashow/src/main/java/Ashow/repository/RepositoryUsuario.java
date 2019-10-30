package Ashow.repository;

        import Ashow.business.Contratante;
        import Ashow.business.Usuario;
        import Ashow.dao.Dao;
        import Ashow.interfac.IRepository;

        import java.io.IOException;
        import java.io.Serializable;

public class RepositoryUsuario implements IRepository, Serializable {

    private static final String FILE = "src/main/java/Ashow/bin/usuario.bin";
    public final Dao<Usuario, Integer> CONTRATANTE_DAO = inicializarDao();

    @Override
    public Dao inicializarDao() {
        try {
            Dao dao = new Dao(FILE);
            return dao;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
