package Ashow.dao;

import Ashow.interfac.IDao;
import Ashow.interfac.UtilitarioDoDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Dao<T extends UtilitarioDoDao<K>, K> implements IDao<T, K>, Serializable {
    private List<T> dados;
    private File file;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    public Dao(String filename) throws IOException {
        file = new File(filename);
        dados = readFromFile();
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

    @Override
    public T get(K k) {
        for (T t : dados)
            if (t.isID(k)) {
                return t;
            }
        return null;
    }

    public T get(String k) {
        for (T t : dados)
            if (t.getEmail().equals(k)) {
                return t;
            }
        return null;
    }

    @Override
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

    @Override
    public boolean add(T t) {
        dados.add(t);
        return saveInFile();
    }


    @Override
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

    @Override
    public List<T> getAll() {
        return readFromFile();
    }
}
