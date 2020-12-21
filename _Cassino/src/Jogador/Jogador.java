package Jogador;
import Excecoes.IdadeInvalidaException;
import Excecoes.SaldoInvalidoException;
public class Jogador {
    private String nome;
    private String cpf;
    private int idade;
    private double saldo;
    private String senha;
    public Jogador(String nome, String cpf, int idade, double saldo, String senha) throws IdadeInvalidaException, SaldoInvalidoException {
        this.nome = nome;
        this.cpf = cpf;
        if(idade < 18){
            throw new IdadeInvalidaException();
        }
        this.idade = idade;
        if(saldo < 0){
            throw new SaldoInvalidoException();
        }
        this.saldo = saldo;
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public int getIdade() {
        return idade;
    }
    public void setSaldo(double novoSaldo) {
        this.saldo += novoSaldo;
    }
    public double getSaldo() {
        return saldo;
    } 
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public double pagarJogo(Double precoJogo){
        if(getSaldo()-precoJogo < 0) return 0;
        setSaldo(-precoJogo);
        return precoJogo;
    }
}




