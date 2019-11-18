package br.com.dao;

import br.com.business.Usuario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DaoUsuario extends Dao<Usuario, Integer> implements Serializable {
  private FileOutputStream fileOutputStream;
  private ObjectOutputStream objectOutputStream;

  public DaoUsuario(String filename) throws IOException {
    super(filename);
    if (!dados.isEmpty())
      Usuario.setMaxID(dados.stream().mapToInt(Usuario::getID).max().getAsInt());
  }

  @Override
  public Usuario get(Integer k) {
    for (Usuario t : dados)
      if (t.isID(k)) {
        return t;
      }
    return null;
  }
}
