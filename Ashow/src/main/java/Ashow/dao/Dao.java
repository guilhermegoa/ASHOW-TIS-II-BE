package Ashow.dao;

import Ashow.business.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    public boolean update(T t) {
        ListIterator<T> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().isID(t.getID())) {
                dados.set(iterator.nextIndex() - 1, t);
                return saveInFile();
            }
        }
        return false;
    }

    public boolean add(T t) {
        dados.add(t);
        return saveInFile();
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
