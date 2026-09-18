package estruturas;

import java.util.List;

import excecoes.FilaVaziaException;

public interface IFila<T> {
    
    void enqueue(T elemento);

    /**
     * Remove e retorna o primeiro elemento da fila.
     * @throws FilaVaziaException se a fila estiver vazia.
     */
    T dequeue() throws FilaVaziaException;

    /**
     * Retorna o primeiro elemento sem o remover.
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

    /**
     * Retorna uma cópia dos elementos, do primeiro ao último, sem alterar a fila.
     * Serve para consulta/visualização (ex.: GUI).
     */
    List<T> toList();
}
