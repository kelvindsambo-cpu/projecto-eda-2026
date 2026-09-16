package estruturas;

public class Fila {
    private No inicio;
    private No fim;
    private int tamanho;

    public Fila(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    public boolean isEmpty(){
        return tamanho == 0;
    }
    public int tamanho(){
        return tamanho;
    }
    public void adicionar(Paciente paciente){
        No novoNo = new No(paciente);
        if(isEmpty()){
            inicio = novoNo;
            fim = novoNo;
            tamanho++;
            return;
        }
        fim.setProximo(novoNo);
        fim = novoNo;
        tamanho++;
    }
    public Paciente remover(){
        if(isEmpty()){
            return null;
        }
        Paciente paciente = inicio.getPaciente();
        inicio = inicio.getProximo();
        tamanho--;
        if(inicio == null){
            fim = null;
        }
        return paciente;
    }
    
    public Paciente primeiro(){
        if(isEmpty()){
            return null;
        }
        return inicio.getPaciente();
    }
}
