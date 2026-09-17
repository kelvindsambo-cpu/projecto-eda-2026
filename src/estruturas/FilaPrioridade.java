package estruturas;

import excecoes.FilaVaziaException;
import excecoes.TriagemPendenteException;
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

        final int prioridade;
        try {
            prioridade = paciente.getPrioridade();
        } catch (TriagemPendenteException e) {
            throw new IllegalArgumentException(
                    "Só pacientes triados podem entrar na fila de prioridade.", e);
        }

        if (isEmpty() || prioridade < prioridadeDo(inicio.getPaciente())) {
            novo.setProximo(inicio);
            inicio = novo;
        } else {
            No atual = inicio;
            while (atual.getProximo() != null
                    && prioridadeDo(atual.getProximo().getPaciente()) <= prioridade) {
                atual = atual.getProximo();
            }
            novo.setProximo(atual.getProximo());
            atual.setProximo(novo);
        }
        tamanho++;
    }

    private int prioridadeDo(Paciente paciente) {
        try {
            return paciente.getPrioridade();
        } catch (TriagemPendenteException e) {
            throw new IllegalStateException(
                    "A fila de prioridade contém um paciente sem triagem.", e);
        }
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
