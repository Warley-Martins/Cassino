package Jogos;
public class Figura {
    private int valor;
    private int premio;
    public Figura(int valor, int premio) {
        this.valor = valor;
        this.premio = premio;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public int getPremio() {
        return premio;
    }
    public void setPremio(int premio) {
        this.premio = premio;
    }

    @Override
    public String toString() {
        return "*******"
           + "\n*  " + valor + "  *"
           + "\n*******";
    }
    
}





