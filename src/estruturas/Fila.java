package estruturas;

import excecoes.FilaVaziaException;
import modelo.Paciente;
public class Fila implements IFila<Paciente> {
    private No inicio;
    private No fim;
    private int tamanho;
    public Fila(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    public boolean isEmpty() {
        return this.tamanho == 0;
    }
    public int size(){
        return this.tamanho;
    }
    public void enqueue(Paciente elemento){
        No novoNo = new No(elemento);
        if(isEmpty()){
            this.inicio = novoNo;
            this.fim = novoNo;
            this.tamanho++;
            return;
        }
        this.fim.setProximo(novoNo);
        this.fim = novoNo;
        this.tamanho++;
    }
    public Paciente dequeue() throws FilaVaziaException {
        if(isEmpty()){
            throw new FilaVaziaException("A fila está vazia.");
        }
        Paciente paciente = this.inicio.getPaciente();
        this.inicio = this.inicio.getProximo();
        this.tamanho--;
        if(this.inicio == null){
            this.fim = null;
        }
        return paciente;
    }
    public Paciente peek() throws FilaVaziaException {
        if(isEmpty()){
            throw new FilaVaziaException("A fila está vazia.");
        }
        return this.inicio.getPaciente();
    }
}
