package br.com.repository;

import br.com.business.Artista;
import br.com.business.Contratante;
import br.com.business.Usuario;
import br.com.dao.Dao;
import br.com.dao.DaoEventos;
import br.com.dao.DaoUsuario;
import java.io.IOException;
import java.io.Serializable;

public class Repository implements Serializable {

  private static final String FILE = "src/main/java/br/com/bin/usuarios.bin";
  private static final String FILEARTISTAS = "src/main/java/br/com/bin/artistas.bin";
  private static final String FILECONTRATANTE = "src/main/java/br/com/bin/contratantes.bin";
  private static final String FILEEVENTOS = "src/main/java/br/com/bin/eventos.bin";
  public final Dao<Usuario, Integer> daoUsuarios = inicializarDao(FILE);
  public final Dao<Artista, Integer> daoArtistas = inicializarDao(FILEARTISTAS);
  public final Dao<Contratante, Integer> daoContratantes = inicializarDao(FILECONTRATANTE);
  public final DaoEventos daoEventos = inicializarDaoEvento(FILEEVENTOS);

  private DaoEventos inicializarDaoEvento(String filecontratante) {
    try {
      return new DaoEventos(filecontratante);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Dao inicializarDao(String arqv) {
    try {
      return new DaoUsuario(arqv);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
