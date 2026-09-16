package modelo;

import java.time.LocalDateTime;

import excecoes.PacienteInvalidoException;
import util.Validacao;

public class Triagem {
    private Paciente paciente;
    private String sintomas;
    private NivelTriagem nivel;
    private LocalDateTime dataHoraTriagem;

    public Triagem(Paciente paciente, String sintomas, NivelTriagem nivel) throws PacienteInvalidoException {
        Validacao.validarTriagem(paciente, sintomas, nivel);

        this.paciente = paciente;
        this.sintomas = sintomas;
        this.nivel = nivel;
        this.dataHoraTriagem = LocalDateTime.now();

        this.paciente.setSintomas(sintomas);
        this.paciente.setNivel(nivel);
    }

    // Getters
    public Paciente getPaciente() {
        return paciente;
    }

    public String getSintomas() {
        return sintomas;
    }

    public NivelTriagem getNivel() {
        return nivel;
    }

    public LocalDateTime getDataHoraTriagem() {
        return dataHoraTriagem;
    }

    @Override
    public String toString() {
        return "Triagem - Paciente: " + paciente.getNome() +
                " | Nível: " + nivel.getCategoria() +
                " (" + nivel.getCor() + ")";
    }
}