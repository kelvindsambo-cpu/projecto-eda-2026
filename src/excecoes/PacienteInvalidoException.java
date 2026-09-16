package excecoes;

public class PacienteInvalidoException extends Exception {
    public PacienteInvalidoException(String mensagem) {
        super(mensagem);
    }
}