package Ashow.business;

import java.io.Serializable;

public class Endereco implements Serializable {
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
    private String Rua;
    private String cep;

    public Endereco(){}

    public Endereco(String cep, String rua, String num, String bairro, String cidade, String uf, String complemento) {
        setCep(cep);
        setRua(rua);
        setNumero(num);
        setBairro(bairro);
        setCidade(cidade);
        setUf(uf);
        setComplemento(complemento);
    }

    public Endereco(String cep, String rua, String num, String bairro, String cidade, String uf) {
        setCep(cep);
        setRua(rua);
        setNumero(num);
        setBairro(bairro);
        setCidade(cidade);
        setUf(uf);
        setComplemento("");
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "{Rua: " + this.Rua + ", " + this.numero + ", " + this.bairro +
                    ", " + this.cidade + " - " + this.uf + "\n CEP: " + this.cep + "\nComplemento: " + this.complemento+"}";
    }
}
