package src.modelo;

public class Paciente{
    private int id;
    private String nome;
    private int idade;

    private String sintomas;
    private int prioridade;
    private boolean triado;

    private long horaChegada;

    public Paciente( int id, String nome, int idade){
        this.id= id;
        this.nome=nome;
        this.idade=idade;
        this.horaChegada= System.currentTimeMillis();
        this.triado=false;
        this.prioridade=5;
    }

    public int getId(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }

    public int getIdade(){
        return idade;
    }

    public int getPrioridade(){
        return prioridade;
    }

    public long getHoraChegada(){
        return horaChegada;
    }

    public boolean isTriado(){
        return triado;
    }

    public void setSintomas (String sintomas){
        this.sintomas=sintomas;
    }

    public void setPrioridade(int prioridade){
        this.prioridade=prioridade;
    }

    public void setTriado(boolean triado){
        this.triado=triado;
    }

    @Override 
    public String toString(){
        return "Paciente \n id: "+ id + "\n Nome: "+ nome + "\n Idade: "+ idade +
        "\n Sintomas: "+ sintomas +"\n Prioridade: "+prioridade +"\n triado: "+ triado ; 
    }

    public void setNivelTriagem(NivelTriagem nivel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNivelTriagem'");
    }
}