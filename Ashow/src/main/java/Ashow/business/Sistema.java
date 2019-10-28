package Ashow.business;

import Ashow.repository.RepositoryArtista;
import Ashow.repository.RepositoryContratante;

public class Sistema {

    public static final RepositoryArtista artistaDao = initArtista();
    public static final RepositoryContratante contratanteDao = initContratante();

    public static RepositoryArtista initArtista() {
        RepositoryArtista a = new RepositoryArtista();
        a.ARTISTA_DAO.add(new Artista("Matheus","Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
        a.ARTISTA_DAO.add(new Artista("Guilherme","GOA", "12345", "goa@email.com", "Pagodeiro", "Solo"));
        return a;
    }
    public static RepositoryContratante initContratante() {
        RepositoryContratante c = new RepositoryContratante();
        c.CONTRATANTE_DAO.add(new Contratante("Shulambs", "12345", "contro@gmail.com"));
        return c;
    }

    public static void main(String[] args) {
        System.out.println(artistaDao.ARTISTA_DAO.getAll().toString());
    }
}
