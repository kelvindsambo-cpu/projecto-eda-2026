package servicos;

import excecoes.FilaVaziaException;
import excecoes.PacienteInvalidoException;
import excecoes.TriagemPendenteException;
import estruturas.Fila;
import estruturas.FilaPrioridade;
import modelo.Atendimento;
import modelo.EstadoAtendimento;
import modelo.NivelTriagem;
import modelo.Paciente;
import modelo.Triagem;
import util.Validacao;

/**
 * Coordena o fluxo de atendimento sem expor a implementação das filas.
 */
public class GestorAtendimento {
    private final Fila filaNormal;
    private final FilaPrioridade filaPrioridade;
    private Atendimento atendimentoAtual;

    public GestorAtendimento() {
        this.filaNormal = new Fila();
        this.filaPrioridade = new FilaPrioridade();
    }

    public Paciente registarPaciente(int id, String nome, int idade)
            throws PacienteInvalidoException {
        return new Paciente(id, nome, idade);
    }

    public void registarPaciente(Paciente paciente) throws PacienteInvalidoException {
        Validacao.validarPacienteNaoNulo(paciente);
    }

    public Triagem realizarTriagem(Paciente paciente, String sintomas, NivelTriagem nivel)
            throws PacienteInvalidoException {
        registarPaciente(paciente);
        Triagem triagem = new Triagem(paciente, sintomas, nivel);
        encaminharPaciente(paciente);
        return triagem;
    }

    public Triagem triagem(Paciente paciente, String sintomas, NivelTriagem nivel)
            throws PacienteInvalidoException {
        return realizarTriagem(paciente, sintomas, nivel);
    }

    public void encaminharPaciente(Paciente paciente) throws PacienteInvalidoException {
        Validacao.validarPacienteNaoNulo(paciente);
        if (!paciente.isTriado()) {
            throw new IllegalArgumentException(
                    "Não é possível encaminhar um paciente sem triagem.");
        }

        if (paciente.getNivel().isPrioritario()) {
            filaPrioridade.enqueue(paciente);
        } else {
            filaNormal.enqueue(paciente);
        }
    }

    public void encaminhar(Paciente paciente) throws PacienteInvalidoException {
        encaminharPaciente(paciente);
    }

    public Paciente chamarProximo() throws FilaVaziaException {
        if (!filaPrioridade.isEmpty()) {
            return filaPrioridade.dequeue();
        }
        return filaNormal.dequeue();
    }

    public Atendimento chamarProximo(String medico)
            throws FilaVaziaException, TriagemPendenteException, PacienteInvalidoException {
        Paciente paciente = chamarProximo();
        return iniciarAtendimento(paciente, medico);
    }

    public Atendimento iniciarAtendimento(Paciente paciente, String medico)
            throws TriagemPendenteException, PacienteInvalidoException {
        if (atendimentoAtual != null
                && atendimentoAtual.getEstado() == EstadoAtendimento.EM_ATENDIMENTO) {
            throw new IllegalStateException("Já existe um atendimento em curso.");
        }
        atendimentoAtual = new Atendimento(paciente, medico);
        return atendimentoAtual;
    }

    public void finalizarAtendimento(String observacoes) {
        if (atendimentoAtual == null
                || atendimentoAtual.getEstado() != EstadoAtendimento.EM_ATENDIMENTO) {
            throw new IllegalStateException("Não existe atendimento em curso.");
        }
        atendimentoAtual.finalizar(observacoes);
        atendimentoAtual = null;
    }

    public void finalizar(Atendimento atendimento, String observacoes) {
        if (atendimento == null) {
            throw new IllegalArgumentException("O atendimento não pode ser nulo.");
        }
        atendimento.finalizar(observacoes);
        if (atendimento == atendimentoAtual) {
            atendimentoAtual = null;
        }
    }

    public Atendimento getAtendimentoAtual() {
        return atendimentoAtual;
    }

    public Fila getFilaNormal() {
        return filaNormal;
    }

    public FilaPrioridade getFilaPrioridade() {
        return filaPrioridade;
    }
}
