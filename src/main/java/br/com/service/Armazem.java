package br.com.service;

public class Armazem {
    private int art;
    private int cont;
    private double valor;
    private double valorEvento;
    private int even;

    public int getEven() {
        return even;
    }

    public void setEven(int even) {
        this.even = even;
    }

    public double getValorEvento() {
        return valorEvento;
    }

    public void setValorEvento(double valorEvento) {
        this.valorEvento = valorEvento;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getArt() {
        return art;
    }

    public void setArt(int art) {
        this.art = art;
    }

    public Armazem(){

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Armazem(int art, int cont, double valor, double valorEvento, int even){
        setArt(art);
        setCont(cont);
        setValor(valor);
        setValorEvento(valorEvento);
        setEven(even);
    }
}
