package Ashow.business;

import Ashow.Repository.RepositoryArtista;

public class Sistema {

    public static final RepositoryArtista artistaDao = initArtista();

    public static RepositoryArtista initArtista() {
        RepositoryArtista a = new RepositoryArtista();
//        a.ARTISTA_DAO.add(new Artista("Matheus","Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
//        a.ARTISTA_DAO.add(new Artista("Guilherme","GOA", "12345", "goa@email.com", "Pagodeiro", "Solo"));
        return a;
    }

    public static void main(String[] args) {
        System.out.println(artistaDao.ARTISTA_DAO.getAll().toString());
    }
}
