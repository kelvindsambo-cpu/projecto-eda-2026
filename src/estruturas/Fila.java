package estruturas;

import java.util.ArrayList;
import java.util.List;

import excecoes.FilaVaziaException;
import modelo.Paciente;

/**
 * Fila FIFO implementada com lista ligada (referências para início e fim).
 * enqueue, dequeue, peek, isEmpty e size são O(1); toList é O(n).
 */
public class Fila implements IFila<Paciente> {
    private No<Paciente> inicio;
    private No<Paciente> fim;
    private int tamanho;

    public Fila() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.tamanho == 0;
    }

    @Override
    public int size() {
        return this.tamanho;
    }

    @Override
    public void enqueue(Paciente elemento) {
        No<Paciente> novoNo = new No<>(elemento);
        if (isEmpty()) {
            this.inicio = novoNo;
            this.fim = novoNo;
            this.tamanho++;
            return;
        }
        this.fim.setProximo(novoNo);
        this.fim = novoNo;
        this.tamanho++;
    }

    @Override
    public Paciente dequeue() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila está vazia.");
        }
        Paciente paciente = this.inicio.getElemento();
        this.inicio = this.inicio.getProximo();
        this.tamanho--;
        if (this.inicio == null) {
            this.fim = null;
        }
        return paciente;
    }

    @Override
    public Paciente peek() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila está vazia.");
        }
        return this.inicio.getElemento();
    }

    @Override
    public List<Paciente> toList() {
        List<Paciente> lista = new ArrayList<>();
        No<Paciente> atual = this.inicio;
        while (atual != null) {
            lista.add(atual.getElemento());
            atual = atual.getProximo();
        }
        return lista;
    }
}
