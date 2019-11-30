package br.com.dao;

import br.com.business.Avaliacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class DaoAvaliacao implements Serializable {

    List<Avaliacao> dados;
    File file;
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;

    public DaoAvaliacao(String filename) throws IOException {
        file = new File(filename);
        dados = readFromFile();
        if (dados.stream().mapToInt(Avaliacao::getIdEvento).count() > 0) {
            Avaliacao.setMaxId(dados.stream().mapToInt(Avaliacao::getIdEvento).max().getAsInt());
        } else Avaliacao.setMaxId(0);
    }

    public File getFile() {
        return file;
    }

    private List<Avaliacao> readFromFile() {
        dados = new ArrayList<Avaliacao>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            dados = (List<Avaliacao>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("opc 1");
            saveInFile();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("opc 2");
            e.printStackTrace();
        }
        return dados;
    }

    public boolean saveInFile() {
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

    public Avaliacao get(int k) {
        for (Avaliacao t : dados)
            if (t.getIdEvento() == (k)) {
                return t;
            }
        return null;
    }

    public boolean add(Avaliacao avaliacao) {
        if (getAll().stream().anyMatch(a -> a.equals(avaliacao))) return false;
        else return dados.add(avaliacao) && saveInFile();
    }

    public boolean remove(Avaliacao avaliacao) {
        ListIterator<Avaliacao> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIdEvento() == (avaliacao.getIdEvento())) {
                iterator.remove();
                return saveInFile();
            }
        }
        return false;
    }

    public List<Avaliacao> getAll() {
        return readFromFile();
    }

    @Override
    protected void finalize() throws Throwable {
        saveInFile();
        super.finalize();
    }


}
