package Excecoes;
public class SaldoInvalidoException extends Exception{
    public SaldoInvalidoException() {
        super("Saldo invalido");
    }
    public SaldoInvalidoException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage(); 
    }
}



