package Ashow.dao;

import Ashow.interfac.IDao;
import Ashow.business.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IDao<Usuario, String> {
    private List<Usuario> infos;
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public UsuarioDAO(String filename) throws IOException {

        infos = new ArrayList<Usuario>();

        file = new File(filename);
        readFromFile();
    }

    public void add(Usuario user) {
        try {
            infos.add(user);
            saveToFile();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o produto '" + user.getNome() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Usuario get(String chave) {
        Usuario user = null;

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                user = (Usuario) inputFile.readObject();

                if (chave.equals(user.getNome())) {
                    return user;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o produto '" + chave + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Usuario p) {
        int index = infos.indexOf(p);
        if (index != -1) {
            infos.set(index, p);
            saveToFile();
        }
        return false;
    }

    public boolean remove(Usuario p) {
        int index = infos.indexOf(p);
        if (index != -1) {
            infos.remove(index);
            saveToFile();
        }
        return false;
    }

    public List<Usuario> getAll() {
        return infos;
    }

    private List<Usuario> readFromFile() {
        Usuario user = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream inputFile = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                user = (Usuario) inputFile.readObject();
                infos.add(user);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler produto no disco!");
            e.printStackTrace();
        }
        return infos;
    }

    private void saveToFile() {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Usuario produto : infos) {
                outputFile.writeObject(produto);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar produto no disco!");
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        if (outputFile != null ) {
            outputFile.close();
            fos.close();
            outputFile = null;
            fos = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }

}


