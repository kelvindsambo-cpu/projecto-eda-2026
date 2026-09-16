package estruturas;

import excecoes.FilaVaziaException;

public interface IFila<T> {

    /**
     * Adiciona um elemento ao final da fila (ou na posição correta por prioridade).
     */
    void enqueue(T elemento);

    /**
     * Remove e retorna o primeiro elemento da fila.
     * 
     * @throws FilaVaziaException se a fila estiver vazia.
     */
    T dequeue() throws FilaVaziaException;

    /**
     * Retorna o primeiro elemento sem o remover.
     * 
     * @throws FilaVaziaException se a fila estiver vazia.
     */
    T peek() throws FilaVaziaException;

    /**
     * Verifica se a fila está vazia.
     */
    boolean isEmpty();

    /**
     * Retorna o número de elementos na fila.
     */
    int size();
}