import excecoes.PacienteInvalidoException;
import estruturas.Fila;
import modelo.Paciente;

public class TesteFila {
    public static void main(String[] args) throws PacienteInvalidoException {

        Fila fila = new Fila();

        Paciente p1 = new Paciente(0, "Joao", 18);
        Paciente p2 = new Paciente(1, "Maria", 30);
        Paciente p3 = new Paciente(2, "Afonso", 30);
        //Paciente p4 = new Paciente(3,"Carlos",-5);
        //Paciente p5 = new Paciente(4, "Pedro", -6);

        fila.enqueue(p1);
        fila.enqueue(p2);
        fila.enqueue(p3);
        //fila.enqueue(p4);
        //fila.enqueue(p5);

        System.out.println("Tamanho: " + fila.size());

        try {
            System.out.println("Primeiro: " + fila.peek());

            System.out.println("Saiu: " + fila.dequeue());
            System.out.println("Saiu: " + fila.dequeue());

            System.out.println("Tamanho: " + fila.size());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
