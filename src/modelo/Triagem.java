package src.modelo;

public class Triagem {
    private Paciente paciente;
    private String sintomas;
    private NivelTriagem nivel;
    
    public Triagem ( Paciente paciente, String sintomas, NivelTriagem nivel){
        this.paciente= paciente;
        this.sintomas= sintomas;
        this.nivel= nivel;

        this.paciente.setSintomas(sintomas);
        this.paciente.setNivelTriagem (nivel);
        this.paciente.setTriado(true);
        
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getSintomas() {
        return sintomas;
    }

    public NivelTriagem getNivel() {
        return nivel;
    }

    
    
    @Override 
    public String toString() {
        return "Triagem \n "+ paciente.getNome() +
             "\n Prioridade: "+nivel.getDescricao() ;
    }

}
