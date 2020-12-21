package Jogos;
import Interface.IJogar;
import Jogador.Jogador;
import java.util.Random;
public class CacaNiquel extends Jogo implements IJogar {
    private Figura[] figuras = new Figura[5];
    public CacaNiquel(double preco) {
        super(preco);
    }
    public Figura[] sortear(){
        Random random = new Random();
        int[] numeroAleatorio = new int[3];
        for(int i = 0; i < 3; i++){
            numeroAleatorio[i] = random.nextInt(5);                  
        }
        Figura[] figurasAux = new Figura[3];
        figurasAux[0] = this.figuras[numeroAleatorio[0]];
        figurasAux[1] = this.figuras[numeroAleatorio[1]];
        figurasAux[2] = this.figuras[numeroAleatorio[2]];
        return figurasAux;
    }
    public boolean ConferirNumGerado(int[] numGerado, int x){
        for(int i = 0; i < x; i++)
            if(numGerado[i] == numGerado[x])
                return false;
        return true;
    }
    public Figura[] getFiguras() {
        return figuras;
    }
    @Override
    public void iniciarJogo(Jogador jogador) {
        this.figuras[0] = new Figura(0, 10);
        this.figuras[1] = new Figura(1,15);
        this.figuras[2] = new Figura(2,30);
        this.figuras[3] = new Figura(3,50);
        this.figuras[4] = new Figura(4,100);
    }
    @Override
    public void finalizarJogo() {
        sortear();
    }
}

















