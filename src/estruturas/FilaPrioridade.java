package estruturas;

import java.util.ArrayList;
import java.util.List;

import excecoes.FilaVaziaException;
import excecoes.TriagemPendenteException;
import modelo.Paciente;

/**
 * Fila ordenada por prioridade. Números menores têm prioridade maior.
 * Em caso de empate, o novo paciente fica depois dos que já lá estão (FIFO),
 * pelo que a ordem de chegada é dada pela ordem de inserção.
 * enqueue é O(n); dequeue, peek, isEmpty e size são O(1); toList é O(n).
 */
public class FilaPrioridade implements IFila<Paciente> {
    private No<Paciente> inicio;
    private int tamanho;

    @Override
    public void enqueue(Paciente paciente) {
        No<Paciente> novoNo = new No<>(paciente);

        final int prioridade;
        try {
            prioridade = paciente.getPrioridade();
        } catch (TriagemPendenteException e) {
            throw new IllegalArgumentException(
                    "Só pacientes triados podem entrar na fila de prioridade.", e);
        }

        if (isEmpty() || prioridade < prioridadeDo(inicio.getElemento())) {
            novoNo.setProximo(inicio);
            inicio = novoNo;
        } else {
            No<Paciente> atual = inicio;
            while (atual.getProximo() != null
                    && prioridadeDo(atual.getProximo().getElemento()) <= prioridade) {
                atual = atual.getProximo();
            }
            novoNo.setProximo(atual.getProximo());
            atual.setProximo(novoNo);
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
        Paciente paciente = inicio.getElemento();
        inicio = inicio.getProximo();
        tamanho--;
        return paciente;
    }

    @Override
    public Paciente peek() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila de prioridade está vazia.");
        }
        return inicio.getElemento();
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public List<Paciente> toList() {
        List<Paciente> lista = new ArrayList<>();
        No<Paciente> atual = inicio;
        while (atual != null) {
            lista.add(atual.getElemento());
            atual = atual.getProximo();
        }
        return lista;
    }
}
