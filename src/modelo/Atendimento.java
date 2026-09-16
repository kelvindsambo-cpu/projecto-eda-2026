package src.modelo;

public class Atendimento {
    private static int geradorId=1;

    private int id;
    private Paciente paciente;
    private String medico;
    private long horaInicio;
    private long horaFim;
    private EstadoAtendimento estado;
    private String observacoes;

    
    public Atendimento ( Paciente paciente, String medico){
        this.id= geradorId++;
        this.paciente= paciente;
        this.medico= medico;
        this.horaInicio= System.currentTimeMillis();
        this.estado= EstadoAtendimento.EM_ATENDIMENTO;
        this.horaFim= 0;
        this.observacoes= " ";
    }

    public long getDuracaoSegundos(){
        long fim;
        if (estado== EstadoAtendimento.EM_ATENDIMENTO){
            fim=System.currentTimeMillis();
        } else {
            fim= horaFim;
        }
        return (fim-horaInicio)/1000;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getMedico() {
        return medico;
    }

    public long getHoraInicio() {
        return horaInicio;
    }

    public EstadoAtendimento estado() {
        return estado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void finalizar ( String observacoes){
        this.horaFim= System.currentTimeMillis();
        this.estado=EstadoAtendimento.ATENDIDO;
        this.observacoes= observacoes;
    }

    @Override 
    public String toString() {
        return "Atendimento \n id: " + id+ "\n Paciente: " + paciente.getNome()+
                "\n Médico " + medico + "\n emCurso: " + estado ;

    }
    
}
