package cassino;
import Interface.IJogar;
import Jogador.Jogador;
import Jogos.BlackJack;
import Jogos.CacaNiquel;
import java.util.ArrayList;
public class Cassino {
    private BlackJack blackJack;
    private CacaNiquel cacaNiquel;
    private ArrayList<Jogador> jogadores;
    public Cassino() {
        this.jogadores = new ArrayList<Jogador>();
        this.blackJack = new BlackJack(10);
        this.cacaNiquel = new CacaNiquel(15);
    }
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
    public Jogador realizarLogin(String cpf, String senha){
        return jogadores.stream()
                        .filter(
                                jog -> jog.getCpf().equals(cpf)
                             && jog.getSenha().equals(senha)
                        )
                        .findFirst()
                        .orElse(null);
    }
    public boolean adicionaJogador(Jogador jogador){
        if(jogador == null) return false;
        this.jogadores.add(jogador);
        return true;
    }
    public boolean adionarSaldoJogador(Jogador jogador, double novoSaldo){
        if(novoSaldo < 0) return false;
        jogador.setSaldo(novoSaldo);
        return true;
    }
    public BlackJack getBlackJack() {
        return blackJack;
    }
    public CacaNiquel getCacaNiquel() {
        return cacaNiquel;
    }
    private void darPremio(Jogador jogador, IJogar jogo){
        var premio = jogo.getPreco()*2;
        jogador.setSaldo(premio);
    }
    public boolean iniciarJogo(Jogador jogador, IJogar jogo){
        var precoJogo = jogo.getPreco();
        var resposta = jogador.pagarJogo(precoJogo);
        if(resposta == 0) return false;
        jogo.iniciarJogo(jogador);
        return true;
    }
    public void finalizarJogo(Jogador jogador, IJogar jogo, boolean resultado){
        if(resultado) darPremio(jogador,jogo);
        jogo.finalizarJogo();
    }
}

















