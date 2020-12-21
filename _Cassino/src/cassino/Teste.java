package cassino;

import Excecoes.IdadeInvalidaException;
import Excecoes.SaldoInvalidoException;
import Jogador.Jogador;
import Jogos.BlackJack;
import Jogos.CacaNiquel;
import Jogos.Carta;
import Jogos.Figura;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Teste {
    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();
    private static final int VALOR_OPCAO_LOGAR = 1;
    private static final int VALOR_CADASTRO_NOVO_JOGADOR = 2;
    private static final int VALOR_ENCERRA_PROGRAMA = 0;
    private static final int MENOR_VALOR_FUNCIONALIDADES = 1;
    private static final int MAIOR_VALOR_FUNCIONALIDADES = 3;
    private static final int OPCAO_NAO_JOGAR_NOVA_PARTIDA = 0;
    private static final int OPCAO_CONTINUAR_JOGANDO = 1;
    private static final int OPCAO_RECARREGA = 1;
    private static final int OPCAO_NAO_RECARREGA = 0;
    private static final int OPCAO_RECEBER_CARTA = 1;
    private static final int OPCAO_NAO_RECEBER_CARTA = 0;
    public static void main(String[] args) {
        menuApresentacaoPrograma();
        Cassino cassino = new Cassino();
        int opcaoLoginJogador;
        int opcaoJogos;
        do{
            Jogador jogador = null;
            do{
                do{
                    opcaoLoginJogador = atribuirInt(menuLogin());
                }while(opcaoLoginJogador != VALOR_CADASTRO_NOVO_JOGADOR
                    && opcaoLoginJogador != VALOR_OPCAO_LOGAR
                    && opcaoLoginJogador != VALOR_ENCERRA_PROGRAMA);
                switch(opcaoLoginJogador){
                    case 1 -> jogador = realizarLoginJogador(cassino);
                    case 2 -> cadastrarJogador(cassino);
                    case 0 -> {
                        encerrarPrograma();
                        return;
                    }
                }
            }while(jogador == null);
            do{
                do{
                    opcaoJogos = atribuirInt(menuOpcoesJogos());
                }while(opcaoJogos < MENOR_VALOR_FUNCIONALIDADES 
                    && opcaoJogos > MAIOR_VALOR_FUNCIONALIDADES 
                    && opcaoJogos != VALOR_ENCERRA_PROGRAMA);
                switch(opcaoJogos){
                    case 1 -> jogarBlackJack(jogador, cassino);
                    case 2 -> jogarCacaNiquel(jogador, cassino);
                    case 3 -> printarJogador(jogador);
                    case 0 -> {
                        encerrarSecao();
                        jogador = null;
                    }
                }
            }while(jogador != null);
        }while(true);
    }
    private static void menuApresentacaoPrograma(){
        System.out.println("\nProgramação de Computadores 2"
                         + "\nTrabalho final: Cassino"
                         + "\nAutor: Warley Júnio Martins Vieira");
    }
    private static String menuLogin(){
        return  "\nDigite a opcao Desejada:"
              + "\n(1). Realiazar login"
              + "\n(2). Realiazar cadastro"
              + "\n(0). Encerrar"
              + "\nOpção desejada: ";
    }
    private static Jogador realizarLoginJogador(Cassino cassino){
        Jogador jogador = null;
        System.out.println("\nLogin de usuário:"
                         + "\nDigite 'esc' em qualquer input para sair do cadastro!");
        var mensagemCpf = "\nDigite o cpf do usuario: ";
        var cpf = atribuirString(mensagemCpf);
        if(cpf.equals("esc")){
            System.out.println("\nTentativa de login encerrada");
            return null;
        }
        var mensagemSenha = "\nDigite a senha do usuario: ";
        var senha = atribuirString(mensagemSenha);
        if(senha.equals("esc")){
            System.out.println("\nTentativa de login encerrada");
            return null;
        }
        jogador = cassino.realizarLogin(cpf, senha);
        if(jogador == null){
            System.out.println("Jogador não encontrado");
            return null;
        }
        System.out.println("\nLogin realizado com sucesso!");
        printarJogador(jogador);
        return jogador;
}
    private static boolean cadastrarJogador(Cassino cassino){
        System.out.println("\nCadastro do novo usuário:"
                         + "\nDigite 'esc' em qualquer input para sair do cadastro!");
        var mensagemNome = "\nDigite o nome do usuario: ";
        var nome = atribuirString(mensagemNome);
        if(nome.equals("esc")){
            System.out.println("\nCadastro encerrado");
            return false;
        }
        var mensagemCpf = "\nDigite o cpf do usuario: ";
        var cpf = atribuirString(mensagemCpf);
        if(cpf.equals("esc")){
            System.out.println("\nCadastro encerrado");
            return false;
        }
        var mensagemSenha = "\nDigite a senha do usuario: ";
        var senha = atribuirString(mensagemSenha);
        if(senha.equals("esc")){
            System.out.println("\nCadastro encerrado");
            return false;
        }
        var mensagemIdade = "Digite a idade do cliente: ";
        String auxiliarVerificaEsc;
        do{
            auxiliarVerificaEsc = atribuirString(mensagemIdade);
            if(auxiliarVerificaEsc.equals("esc")){
                    System.out.println("Cadastro Encerrado!");
                    return false;
            }
        }while(!verificaInt(auxiliarVerificaEsc));
        int idade = Integer.parseInt(auxiliarVerificaEsc);
        var mensagemSaldo = "Digite o saldo inicial do cliente: ";
        do{
            auxiliarVerificaEsc = atribuirString(mensagemSaldo);
            if(auxiliarVerificaEsc.equals("esc")){
                System.out.println("Cadastro Encerrado!");
                return false;
            }
        }while(!verificaDouble(auxiliarVerificaEsc));
        double saldo = Double.parseDouble(auxiliarVerificaEsc);
        try{
            var jogador = new Jogador(nome, cpf, idade, saldo, senha);
            cassino.adicionaJogador(jogador);               
            return true;
        }catch(IdadeInvalidaException e){
            System.out.println("\nNão foi possivel realizar cadastro:"
                             + "\nExceção: " + e.getMessage());
        }
        catch(SaldoInvalidoException e){
            System.out.println("\nNão foi possivel realizar cadastro:"
                             + "\nExceção: " + e.getMessage());
        }
        return false;
    }
    private static String menuOpcoesJogos(){
        return  "\nDigite a opcao Desejada:"
              + "\n(1). Jogar BlackJack R$10,00"
              + "\n(2). Jogar Caça Niquel R$15,00"
              + "\n(3). Ver dados do jogador"
              + "\n(0). Encerrar seção"
              + "\nOpção desejada: ";
    }
    private static String menuNovaPartida(){
        return  "\nDigite a opcao Desejada:"
              + "\n(1). Jogar nova partida"
              + "\n(0). Encerrar"
              + "\nOpção desejada: ";
    }
    private static void jogarBlackJack(Jogador jogador, Cassino cassino){
        int opcaoNovaPartida;
        var blackJack = cassino.getBlackJack();
        do{
            if(cassino.iniciarJogo(jogador, blackJack)){
                System.out.println("\t******* Iniciando Partida *******");
                boolean resultado = false;
                var cartas = new ArrayList<Carta>();
                cartas.add(blackJack.receberCarta());
                cartas.add(blackJack.receberCarta());
                int soma = 0;
                for(var cartaAtual : cartas){
                    System.out.println("\nvalor: " + cartaAtual.getValor()
                                     + "\nNaipe: " + cartaAtual.getNaipe());
                    soma += cartaAtual.getValor();
                }
                do{
                    System.out.println("A soma total atual é: " + soma);
                    if(soma > 21){
                        System.out.println("\nBOOOM!"
                                        +  "\nVocê estou com soma: "+soma);
                        break;
                    }
                    int opcaoReceberCarta;
                    do{
                        var mensagemReceberCarta = "\nDeseja receber uma nova carta?"
                                                 + "\n(1). Sim"
                                                 + "\n(0). Não"
                                                 + "\nOpção desejada: ";
                        opcaoReceberCarta = atribuirInt(mensagemReceberCarta);
                    }while(opcaoReceberCarta != OPCAO_RECEBER_CARTA
                        && opcaoReceberCarta != OPCAO_NAO_RECEBER_CARTA);
                    if(opcaoReceberCarta == OPCAO_NAO_RECEBER_CARTA) break;
                    var novaCarta = blackJack.receberCarta();
                    System.out.println("\nvalor: " + novaCarta.getValor()
                                     + "\nNaipe: " + novaCarta.getNaipe());
                    cartas.add(novaCarta);
                    soma += novaCarta.getValor();
                }while(true);
                int IA;
                do{
                    IA = random.nextInt(21);
                }while(IA < 13);
                if(IA < soma && soma <= 21) resultado = true;
                System.out.println("\tResultado"
                                 + "\nIA: " + IA
                                 + "\nJogador: " + soma
                                 + "\nVoce ganhou: " + resultado);
                cassino.finalizarJogo(jogador, blackJack, resultado);
            }
            else{
                recarregaSaldo(cassino, jogador);
                System.out.println("Partida Encerrada!");
            }    
            do{
                opcaoNovaPartida = atribuirInt(menuNovaPartida());
            }while(opcaoNovaPartida != OPCAO_NAO_JOGAR_NOVA_PARTIDA
                && opcaoNovaPartida != OPCAO_CONTINUAR_JOGANDO);
        }while(opcaoNovaPartida != OPCAO_NAO_JOGAR_NOVA_PARTIDA);
    }
    private static void jogarCacaNiquel(Jogador jogador, Cassino cassino){
        int opcaoNovaPartida;
        var cacaNiquel = cassino.getCacaNiquel();
        do{
            if(cassino.iniciarJogo(jogador, cacaNiquel)){
                System.out.println("\t******* Iniciando Partida *******");
                boolean resultado = false;
                double premio = 0;
                var figuras = new Figura[3];
                figuras = cacaNiquel.sortear();
                System.out.println(figuras[0].toString() +"\n"+ figuras[1].toString() +"\n"+ figuras[2].toString());
                if(figuras[0].getValor() == figuras[1].getValor()
                && figuras[1].getValor() == figuras[2].getValor()){
                    resultado = true;
                    premio += figuras[0].getPremio();
                    jogador.setSaldo(premio);
                    premio += 30;
                }
                System.out.println("\tResultado"
                                 + "\nVoce ganhou: "+ resultado
                                 + "\nPremio: R$" + premio);
                cassino.finalizarJogo(jogador, cacaNiquel, resultado);
            }
            else{
                recarregaSaldo(cassino, jogador);
                System.out.println("Partida Encerrada!");
            }    
            do{
                opcaoNovaPartida = atribuirInt(menuNovaPartida());
            }while(opcaoNovaPartida != OPCAO_NAO_JOGAR_NOVA_PARTIDA
                && opcaoNovaPartida != OPCAO_CONTINUAR_JOGANDO);
        }while(opcaoNovaPartida != OPCAO_NAO_JOGAR_NOVA_PARTIDA);
    }
    private static void recarregaSaldo(Cassino cassino, Jogador jogador){
        var mensagemRecarregaSaldo = "\nOps, Você esta sem saldo para jogar, deseja recarregar?"
                                   + "\n(1). Sim"
                                   + "\n(0). Nao"
                                   + "\nOpção desejada: ";
        int opcaoRecarga;
        do{
            opcaoRecarga = atribuirInt(mensagemRecarregaSaldo);
        }while(opcaoRecarga != OPCAO_RECARREGA
            && opcaoRecarga != OPCAO_NAO_RECARREGA);
        if(opcaoRecarga == OPCAO_NAO_RECARREGA) return;
        double valor;
        do{
            var mensagemSaldoDesejado = "Digite o valor desejado: ";
            valor = atribuirDouble(mensagemSaldoDesejado);
            if(valor < 0) System.out.println("\nOps! O valor digitado é invalido\n");
        }while(valor < 0);
        cassino.adionarSaldoJogador(jogador, valor);
    }
    private static void printarJogador(Jogador jogador){
        System.out.println("\nNome: " + jogador.getNome()
                + "\nCpf:" + jogador.getCpf()
                + "\nIdade: " + jogador.getIdade()
                + "\nSaldo disponivel: R$" + jogador.getSaldo());
    }
    private static boolean verificaInt(String auxiliarVerificaExit) {
        int testeAtribuicaoValor;
            try{
                testeAtribuicaoValor = Integer.parseInt(auxiliarVerificaExit);
                return true;
            }
            catch(Exception e){
                System.out.println("Exeção lançada: " + e.getMessage());
                return false;
            }
    }
    private static int atribuirInt(String mensagem) {
        int valorAtribuido;
        do{
            try{
                System.out.print(mensagem);
                valorAtribuido = Integer.parseInt(input.nextLine());
                return valorAtribuido;
            }
            catch(Exception e){
                System.out.println("Exeção lançada: " + e.getMessage());
            }
        }while(true);
    }
    private static boolean verificaDouble(String auxiliarVerificaExit) {
        double testeAtribuicaoValor;
            try{
                testeAtribuicaoValor = Double.parseDouble(auxiliarVerificaExit);
                return true;
            }
            catch(Exception e){
                System.out.println("Exeção lançada: " + e.getMessage());
                return false;
            }
    }
    private static String atribuirString(String mensagem) {
        String valorAtribuido;
        do{
                System.out.print(mensagem);
                valorAtribuido = input.nextLine();
                if(!valorAtribuido.isEmpty())
                    return valorAtribuido;
        }while(true);
    }
    private static void encerrarPrograma(){
        System.out.println("\nPrograma Encerrado!");
    }
    private static double atribuirDouble(String mensagem) {
        double valorAtribuido;
        do{
            try{
                System.out.print(mensagem);
                valorAtribuido = Double.parseDouble(input.nextLine());
                return valorAtribuido;
            }
            catch(Exception e){
                System.out.println("Exeção lançada: " + e.getMessage());
            }
        }while(true);
    }
    private static void encerrarSecao(){
        System.out.println("Seção encerrada!");
    }
}


























































































