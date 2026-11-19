import java.util.List;

import estruturas.FilaPrioridade;
import excecoes.FilaVaziaException;
import modelo.NivelTriagem;
import modelo.Paciente;

public class TesteFilaPrioridade {
    public static void main(String[] args) throws Exception {
        FilaPrioridade fila = new FilaPrioridade();
        verificar(fila.isEmpty() && fila.size() == 0, "fila inicialmente vazia");

        Paciente urgente = triado(1, "Ana", NivelTriagem.URGENTE);
        Paciente emergencia = triado(2, "Bruno", NivelTriagem.EMERGENCIA);
        Paciente muitoUrgente1 = triado(3, "Carla", NivelTriagem.MUITO_URGENTE);
        Paciente muitoUrgente2 = triado(4, "David", NivelTriagem.MUITO_URGENTE);

        fila.enqueue(urgente);
        fila.enqueue(emergencia);
        fila.enqueue(muitoUrgente1);
        fila.enqueue(muitoUrgente2);
        verificar(fila.size() == 4, "inserir vários pacientes / size()");
        verificar(fila.peek() == emergencia && fila.size() == 4, "peek devolve o mais prioritário sem remover");
        verificar(fila.toList().equals(List.of(emergencia, muitoUrgente1, muitoUrgente2, urgente)),
                "ordenada por nível; empate por ordem de chegada");

        verificar(fila.dequeue() == emergencia
                && fila.dequeue() == muitoUrgente1
                && fila.dequeue() == muitoUrgente2
                && fila.dequeue() == urgente,
                "remover respeitando a prioridade");
        verificar(fila.isEmpty(), "fila vazia após remover tudo");

        boolean lancou = false;
        try {
            fila.dequeue();
        } catch (FilaVaziaException e) {
            lancou = true;
        }
        verificar(lancou, "remover de fila vazia lança FilaVaziaException");

        lancou = false;
        try {
            fila.enqueue(new Paciente(5, "Eva", 40));
        } catch (IllegalArgumentException e) {
            lancou = true;
        }
        verificar(lancou && fila.isEmpty(), "paciente sem triagem é recusado");

        System.out.println("TesteFilaPrioridade: todos os testes passaram.");
    }

    static Paciente triado(int id, String nome, NivelTriagem nivel) throws Exception {
        Paciente paciente = new Paciente(id, nome, 30);
        paciente.setNivel(nivel);
        return paciente;
    }

    static void verificar(boolean condicao, String descricao) {
        if (!condicao) {
            throw new AssertionError("FALHOU: " + descricao);
        }
        System.out.println("OK: " + descricao);
    }
}
