package estruturas;

/**
 * Nó de uma lista simplesmente ligada: guarda um elemento e a referência
 * para o nó seguinte.
 */
public class No<T> {
    private T elemento;
    private No<T> proximo;

    public No(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    public T getElemento() {
        return this.elemento;
    }

    public No<T> getProximo() {
        return this.proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
}
