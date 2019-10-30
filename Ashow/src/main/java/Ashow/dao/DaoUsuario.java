package Ashow.dao;

import Ashow.business.Artista;
import Ashow.business.Contratante;
import Ashow.business.Usuario;
import Ashow.interfac.IDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DaoUsuario<K> implements IDao<Usuario, Integer>, Serializable {
    private List<Usuario> dados;
    private File file;
    private File fileArtista;
    private File fileContratante;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    public DaoUsuario(String filename, String fileArtistaName, String fileContratanteName) throws IOException {
        file = new File(filename);
        fileArtista = new File(fileArtistaName);
        fileContratante = new File(fileContratanteName);
        dados = readFromFiles();
    }

    private List<Usuario> readFromFiles() {
        dados = new ArrayList<Usuario>();
        try {
            FileInputStream fileInputStreamArtista = new FileInputStream(fileArtista);
            FileInputStream fileInputStreamContratante = new FileInputStream(fileContratante);
            ObjectInputStream objectInputStreamArtista = new ObjectInputStream(fileInputStreamArtista);
            ObjectInputStream objectInputStreamContratante = new ObjectInputStream(fileInputStreamContratante);

            List<Artista> dadosArtistas = (List<Artista>) objectInputStreamArtista.readObject();
            List<Contratante> dadosContratante = (List<Contratante>) objectInputStreamContratante.readObject();
            dados.addAll(dadosArtistas);
            dados.addAll(dadosContratante);
            fileInputStreamArtista.close();
            objectInputStreamContratante.close();
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
            objectOutputStream.writeObject(readFromFiles());
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

    @Override
    public Usuario get(Integer k) {
        for (Usuario t : dados)
            if (t.isID(k)) {
                return t;
            }
        return null;
    }

    public Usuario get(String k) {
        for (Usuario t : dados)
            if (t.getEmail().equals(k)) {
                return t;
            }
        return null;
    }

    @Override
    public boolean update(Usuario t) {
        ListIterator<Usuario> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().isID(t.getID())) {
                dados.set(iterator.nextIndex() - 1, t);
                return saveInFile();
            }
        }
        return false;
    }

    @Override
    public boolean add(Usuario t) {
        dados.add(t);
        return saveInFile();
    }


    @Override
    public boolean remove(Usuario t) {
        ListIterator<Usuario> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().isID(t.getID())) {
                iterator.remove();
                return saveInFile();
            }
        }
        return false;
    }

    @Override
    public List<Usuario> getAll() {
        return readFromFiles();
    }
}
