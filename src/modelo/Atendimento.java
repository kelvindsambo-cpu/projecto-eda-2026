package modelo;

import excecoes.PacienteInvalidoException;
import excecoes.TriagemPendenteException;
import util.Validacao;

public class Atendimento {
    private static int geradorId = 1;

    private int id;
    private Paciente paciente;
    private String medico;
    private long horaInicio;
    private long horaFim;
    private EstadoAtendimento estado;
    private String observacoes;

    public Atendimento(Paciente paciente, String medico) throws TriagemPendenteException, PacienteInvalidoException {
        Validacao.validarAtendimento(paciente, medico);

        this.id = geradorId++;
        this.paciente = paciente;
        this.medico = medico;
        this.horaInicio = System.currentTimeMillis();
        this.horaFim = 0;
        this.estado = EstadoAtendimento.EM_ATENDIMENTO;
        this.observacoes = "Em andamento";

        // O Atendimento é a fonte de verdade do estado; Paciente.estado é um espelho para consulta.
        this.paciente.setEstado(EstadoAtendimento.EM_ATENDIMENTO);
    }

    public void finalizar(String observacoes) {
        this.horaFim = System.currentTimeMillis();
        this.estado = EstadoAtendimento.ATENDIDO;
        this.observacoes = (observacoes != null && !observacoes.trim().isEmpty())
                ? observacoes
                : "Sem observações";

        // Sincroniza o estado final no Paciente (espelho do estado do Atendimento)
        this.paciente.setEstado(EstadoAtendimento.ATENDIDO);
    }

    public long getDuracaoSegundos() {
        long fim = (estado == EstadoAtendimento.EM_ATENDIMENTO)
                ? System.currentTimeMillis()
                : horaFim;
        return (fim - horaInicio) / 1000;
    }

    // Getters
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

    public long getHoraFim() {
        return horaFim;
    }

    public EstadoAtendimento getEstado() {
        return estado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
    public String toString() {
        return String.format("Atendimento #%d | Paciente: %s | Médico: %s | Estado: %s | Duração: %ds",
                id, paciente.getNome(), medico, estado, getDuracaoSegundos());
    }
}