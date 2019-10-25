package business;

import java.io.Serializable;
import java.util.Random;

public abstract class Usuario implements Serializable {
    private static int contador = 0;
    private String senha;
    private String email;
    private String nome;
    private int ID;
    private float mediaAvaliacao;
    private Endereco endereco;

    public Usuario(String nome, String senha, String email) {
        setNome(nome);
        setSenha(senha);
        setEmail(email);
        somaUmContadorUsuario();
    }

    private void somaUmContadorUsuario() {
        setContador(getContador() + 1);
    }

    public abstract void addAvaliacao();

    public abstract float calculaMediaAvaliacao();

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Usuario.contador = contador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(float mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


}
