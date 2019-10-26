package dao;

import business.Evento;
import business.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IDAO<Usuario, String> {

    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public UsuarioDAO(String filename) throws IOException {
        file = new File(filename);
//        if (file.exists())
//            file.delete();
        fos = new FileOutputStream(file, false);
        outputFile = new ObjectOutputStream(fos);
    }

    public void add(Usuario usuario) {
        try {
            outputFile.writeObject(usuario);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o user '" + usuario.getNome() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Usuario get(String email) {
        Usuario usuario = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                usuario = (Usuario) inputFile.readObject();

                if (email.equals(usuario.getEmail())) {
                    return usuario;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o user '" + email + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario usuario = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                usuario = (Usuario) inputFile.readObject();
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar user no disco!");
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public boolean update(Usuario p) {
    	int valido = 0;
        List<Usuario> usuarios = getAll();
        int index = usuarios.indexOf(p);
        if (index != -1) {
        	valido = 1;
        	usuarios.set(index, p);
        }
        saveToFile(usuarios);
        return valido == 1? true:false;
    }

    public boolean remove(Usuario p) {
    	int valido = 0;
        List<Usuario> usuarios = getAll();
        int index = usuarios.indexOf(p);
        if (index != -1) {
        	valido = 1;
        	usuarios.remove(index);
        }
        saveToFile(usuarios);
        return valido == 1? true:false;
    }

    private void saveToFile(List<Usuario> usuarios) {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Usuario usuario : usuarios) {
                outputFile.writeObject(usuario);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar user no disco!");
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }

}


