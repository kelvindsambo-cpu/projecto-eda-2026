package estruturas;

import java.util.ArrayList;
import java.util.List;

import excecoes.FilaVaziaException;

/**
 * Fila FIFO implementada com lista ligada (referências para início e fim).
 * enqueue, dequeue, peek, isEmpty e size são O(1); toList é O(n).
 */
public class Fila<T> implements IFila<T> {
    private No<T> inicio;
    private No<T> fim;
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
    public void enqueue(T elemento) {
        No<T> novoNo = new No<>(elemento);
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
    public T dequeue() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila está vazia.");
        }
        T elemento = this.inicio.getElemento();
        this.inicio = this.inicio.getProximo();
        this.tamanho--;
        if (this.inicio == null) {
            this.fim = null;
        }
        return elemento;
    }

    @Override
    public T peek() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila está vazia.");
        }
        return this.inicio.getElemento();
    }

    @Override
    public List<T> toList() {
        List<T> lista = new ArrayList<>();
        No<T> atual = this.inicio;
        while (atual != null) {
            lista.add(atual.getElemento());
            atual = atual.getProximo();
        }
        return lista;
    }
}
