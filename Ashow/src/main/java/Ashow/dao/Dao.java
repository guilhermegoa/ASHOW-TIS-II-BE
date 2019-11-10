package Ashow.dao;

import Ashow.business.Artista;
import Ashow.business.Contratante;
import Ashow.business.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Dao<T extends Usuario, K> implements Serializable {
  List<T> dados;
  File file;
  FileOutputStream fileOutputStream;
  ObjectOutputStream objectOutputStream;

  public Dao(String filename) throws IOException {
    file = new File(filename);
    dados = readFromFile();
  }

  public File getFile() {
    return file;
  }

  private List<T> readFromFile() {
    dados = new ArrayList<T>();
    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      dados = (List<T>) objectInputStream.readObject();
      fileInputStream.close();
      objectInputStream.close();
    } catch (FileNotFoundException e) {
      saveInFile();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return dados;
  }

  private boolean saveInFile() {
    try {
      fileOutputStream = new FileOutputStream(file);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(dados);
      objectOutputStream.flush();
      close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  private void close() throws IOException {
    if (objectOutputStream != null) {
      objectOutputStream.close();
      fileOutputStream.close();
      objectOutputStream = null;
      fileOutputStream = null;
    }
  }

  public abstract T get(K k);

  public T get(String k) {
    for (T t : dados)
      if (t.getEmail().equals(k)) {
        return t;
      }
    return null;
  }

  public boolean update(String email, Artista artistaAlterado) {
    Stream<T> x = dados.stream().filter(o -> o.getEmail().equals(email));
    if (x.anyMatch(o -> o instanceof Artista)) {
      Artista artista =
          (Artista)
              dados.stream()
                  .filter(o -> o.getEmail().equals(email))
                  .filter(o -> o instanceof Artista)
                  .collect(Collectors.toList())
                  .get(0);
      System.out.println("ANTES:" + artista);
      if (artistaAlterado.getNome() != null) artista.setNome(artistaAlterado.getNome());
      if (artistaAlterado.getSenha() != null) artista.setSenha(artistaAlterado.getSenha());
      if (artistaAlterado.getEmail() != null) artista.setEmail(artistaAlterado.getEmail());
      if (artistaAlterado.getEstilo() != null) artista.setEstilo(artistaAlterado.getEstilo());
      if (artistaAlterado.getTipoArtista() != null)
        artista.setTipoArtista(artistaAlterado.getTipoArtista());
      if (artistaAlterado.getNomeArtistico() != null)
        artista.setNomeArtistico(artistaAlterado.getNomeArtistico());
      if (artistaAlterado.getDescricao() != null)
        artista.setDescricao(artistaAlterado.getDescricao());
      if (artistaAlterado.getContatoPublico() != null)
        artista.setContatoPublico(artistaAlterado.getContatoPublico());
      System.out.println("DEPOIS:" + artista);
      return saveInFile();
    } else return false;
  }

  public boolean update(String email, Contratante contratanteAlterado) {
    Stream<T> x = dados.stream().filter(o -> o.getEmail().equals(email));
    if (x.anyMatch(o -> o instanceof Contratante)) {
      Contratante contratante =
          (Contratante)
              dados.stream()
                  .filter(o -> o.getEmail().equals(email))
                  .filter(o -> o instanceof Contratante)
                  .collect(Collectors.toList())
                  .get(0);
      System.out.println("ANTES:" + contratante);
      if (contratanteAlterado.getNome() != null) contratante.setNome(contratanteAlterado.getNome());
      if (contratanteAlterado.getSenha() != null)
        contratante.setSenha(contratanteAlterado.getSenha());
      if (contratanteAlterado.getEmail() != null)
        contratante.setEmail(contratanteAlterado.getEmail());
      System.out.println("DEPOIS:" + contratante);
      return saveInFile();
    } else return false;
  }

  public boolean add(Artista t) {
    boolean jaECadastrado =
        getAll().stream()
            .filter(a -> a.getEmail().equals(t.getEmail()))
            .anyMatch(a -> a instanceof Artista);
    if (jaECadastrado) return false;
    else if (dados.add((T) t)) return saveInFile();
    else return false;
  }

  public boolean add(Contratante t) {
    boolean jaECadastrado =
        getAll().stream()
            .filter(a -> a.getEmail().equals(t.getEmail()))
            .anyMatch(a -> a instanceof Contratante);
    if (jaECadastrado) return false;
    else if (dados.add((T) t)) return saveInFile();
    else return false;
  }

  public boolean remove(T t) {
    ListIterator<T> iterator = dados.listIterator();
    while (iterator.hasNext()) {
      if (iterator.next().isID(t.getID())) {
        iterator.remove();
        return saveInFile();
      }
    }
    return false;
  }

  public List<T> getAll() {
    return readFromFile();
  }
}
