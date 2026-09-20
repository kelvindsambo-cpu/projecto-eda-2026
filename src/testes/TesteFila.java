package testes;

import estruturas.Fila;
import excecoes.FilaVaziaException;
import modelo.Paciente;

public class TesteFila {
    public static void main(String[] args) throws Exception {
        Fila fila = new Fila();
        verificar(fila.isEmpty() && fila.size() == 0, "fila inicialmente vazia");

        Paciente p1 = new Paciente(1, "Joao", 18);
        Paciente p2 = new Paciente(2, "Maria", 30);
        Paciente p3 = new Paciente(3, "Afonso", 30);

        fila.enqueue(p1);
        verificar(fila.size() == 1 && fila.peek() == p1, "inserir um paciente");

        fila.enqueue(p2);
        fila.enqueue(p3);
        verificar(fila.size() == 3, "inserir vários pacientes / size()");
        verificar(fila.peek() == p1 && fila.size() == 3, "peek não remove");
        verificar(fila.toList().equals(java.util.List.of(p1, p2, p3)), "toList na ordem FIFO");

        verificar(fila.dequeue() == p1 && fila.dequeue() == p2 && fila.dequeue() == p3,
                "remover respeitando FIFO");
        verificar(fila.isEmpty(), "fila vazia após remover tudo");

        boolean lancou = false;
        try {
            fila.dequeue();
        } catch (FilaVaziaException e) {
            lancou = true;
        }
        verificar(lancou, "remover de fila vazia lança FilaVaziaException");

        System.out.println("TesteFila: todos os testes passaram.");
    }

    static void verificar(boolean condicao, String descricao) {
        if (!condicao) {
            throw new AssertionError("FALHOU: " + descricao);
        }
        System.out.println("OK: " + descricao);
    }
}
