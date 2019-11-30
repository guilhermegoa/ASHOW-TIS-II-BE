package br.com.dao;

import br.com.business.Notificacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class DaoNotificacao implements Serializable {
    List<Notificacao> dados;
    File file;
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;

    public DaoNotificacao(String filename) throws IOException {
        file = new File(filename);
        dados = readFromFile();
        if (dados.stream().mapToInt(Notificacao::getId).count() > 0) {
            Notificacao.setMaxId(dados.stream().mapToInt(Notificacao::getId).max().getAsInt());
        } else Notificacao.setMaxId(0);
    }

    public File getFile() {
        return file;
    }

    private List<Notificacao> readFromFile() {
        dados = new ArrayList<Notificacao>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            dados = (List<Notificacao>) objectInputStream.readObject();
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

    public Notificacao get(int k) {
        for (Notificacao t : dados)
            if (t.getId() == (k)) {
                return t;
            }
        return null;
    }

    public boolean update(int id, Notificacao notificacao) {
        List<Notificacao> x = dados.stream().filter(o -> o.getId() == (id)).collect(Collectors.toList());
        if (!x.isEmpty()) {
            Notificacao notificacao1 = x.get(0);
            System.out.println("ANTES:" + notificacao1);
            if (notificacao.getIdProposta() != notificacao1.getIdProposta())
                notificacao1.setIdProposta(notificacao.getIdProposta());
            if (notificacao.isVisualizou() != notificacao1.isVisualizou())
                notificacao1.setVisualizou(notificacao.isVisualizou());
            System.out.println("DEPOIS:" + notificacao1);
            return saveInFile();
        } else return false;
    }

    public boolean add(Notificacao proposta) {
        if (getAll().stream().anyMatch(a -> a.equals(proposta))) return false;
        else return dados.add(proposta) && saveInFile();
    }

    public boolean remove(Notificacao proposta) {
        ListIterator<Notificacao> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == (proposta.getId())) {
                iterator.remove();
                return saveInFile();
            }
        }
        return false;
    }

    public List<Notificacao> getAll() {
        return readFromFile();
    }

    @Override
    protected void finalize() throws Throwable {
        saveInFile();
        super.finalize();
    }

    public boolean vizualizar(int id) {
        List<Notificacao> e = dados.stream().filter(o->o.getId() == id).collect(Collectors.toList());
        if (!e.isEmpty()) {
            e.get(0).visualizar();
            return saveInFile();
        } else return false;
    }
}