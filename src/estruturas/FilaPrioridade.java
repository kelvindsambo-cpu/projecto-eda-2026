package estruturas;

import excecoes.FilaVaziaException;
import modelo.Paciente;

/**
 * Fila ordenada por prioridade. Números menores têm prioridade maior.
 */
public class FilaPrioridade implements IFila<Paciente> {
    private No inicio;
    private int tamanho;

    @Override
    public void enqueue(Paciente paciente) {
        No novo = new No(paciente);

        if (isEmpty() || paciente.getPrioridade() < inicio.getPaciente().getPrioridade()) {
            novo.setProximo(inicio);
            inicio = novo;
        } else {
            No atual = inicio;
            while (atual.getProximo() != null
                    && atual.getProximo().getPaciente().getPrioridade() <= paciente.getPrioridade()) {
                atual = atual.getProximo();
            }
            novo.setProximo(atual.getProximo());
            atual.setProximo(novo);
        }
        tamanho++;
    }

    @Override
    public Paciente dequeue() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila de prioridade está vazia.");
        }
        Paciente paciente = inicio.getPaciente();
        inicio = inicio.getProximo();
        tamanho--;
        return paciente;
    }

    @Override
    public Paciente peek() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila de prioridade está vazia.");
        }
        return inicio.getPaciente();
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    @Override
    public int size() {
        return tamanho;
    }
}
