package estruturas;

import modelo.Paciente;

public class No{
    private Paciente paciente;
    private No proximo;
    public No(Paciente paciente){
        this.paciente = paciente;
        this.proximo = null;
    }
    public Paciente getPaciente(){
        return this.paciente;
    }
    public No getProximo(){
        return this.proximo;
    }
    public void setProximo(No proximo){
        this.proximo = proximo;
    }
}
