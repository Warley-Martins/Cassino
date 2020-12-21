package Jogos;
public class Carta {
    private String naipe;
    private int valor;
    public Carta(String naipe, int valor) {
        this.naipe = naipe;
        this.valor = valor;
    }
    public String getNaipe() {
        return naipe;
    }
    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }   
}



