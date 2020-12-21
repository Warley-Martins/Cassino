package Jogos;
import Interface.IJogar;
import Jogador.Jogador;
import java.security.SecureRandom;
import java.util.ArrayList;
public class BlackJack extends Jogo implements IJogar {
    public BlackJack(double preco){
        super(preco);
    String[] naipe =  {"Ouros", "Espadas", "Paus", "Copas"};
        for(int i = 0, k = 0; i < 4; i++){
            for(int j = 0; j < 13; j++,k++){
                cartas[k] = new Carta(naipe[i], (j+1));
            }
        }
    }
    private Carta[] cartas = new Carta[53];
    private int indiceAtual = 0;
    @Override
    public void iniciarJogo(Jogador jogador) {
        embaralhar();
    }
    public Carta receberCarta(){
        var cartaAux = cartas[this.indiceAtual];
        this.indiceAtual++;
        return cartaAux;
    } 
    public void embaralhar(){
        SecureRandom gerador = new SecureRandom();
        Carta cartaAux = new Carta("Coringa", 0);
        int[] numGerado = new int[52];
        for(int i = 0; i < 52; i++){
            numGerado[i] = 0;
            do{
                numGerado[i] = gerador.nextInt(52);                  
            }while(ConferirNumGerado(numGerado, i) == false);
            
                cartaAux = this.cartas[i];
                this.cartas[i] = this.cartas[numGerado[i]];
                this.cartas[numGerado[i]] = cartaAux;                      
        }
    }
    public boolean ConferirNumGerado(int[] numGerado, int x){
        for(int i = 0; i < x; i++)
            if(numGerado[i] == numGerado[x])
                return false;
        return true;
    }
    @Override
    public void finalizarJogo() {
        embaralhar();
    }
    
}












