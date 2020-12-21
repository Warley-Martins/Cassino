package Excecoes;
public class IdadeInvalidaException extends Exception {
    public IdadeInvalidaException(String message) {
        super(message);
    }
    public IdadeInvalidaException() {
        super("Idade invalida para um cassino");
    }   
    @Override
    public String getMessage() {
        return super.getMessage(); 
    }
}





