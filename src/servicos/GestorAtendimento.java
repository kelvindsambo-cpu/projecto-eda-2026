package servicos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import excecoes.FilaVaziaException;
import excecoes.PacienteInvalidoException;
import excecoes.TriagemPendenteException;
import estruturas.Fila;
import estruturas.FilaPrioridade;
import modelo.Atendimento;
import modelo.NivelTriagem;
import modelo.Paciente;
import modelo.Triagem;
import util.Validacao;

/**
 * Coordena o fluxo de atendimento sem expor a implementação das filas.
 * É o único ponto de entrada da GUI: registar → triar → chamar próximo → finalizar.
 */
public class GestorAtendimento {
    private final Fila filaNormal;
    private final FilaPrioridade filaPrioridade;
    private final Map<Integer, Paciente> pacientes;
    private final List<Atendimento> historico;
    private Atendimento atendimentoAtual;
    private int proximoId;

    public GestorAtendimento() {
        this.filaNormal = new Fila();
        this.filaPrioridade = new FilaPrioridade();
        this.pacientes = new LinkedHashMap<>();
        this.historico = new ArrayList<>();
        this.proximoId = 1;
    }

    // ---------- Registo e triagem ----------

    /** Regista um novo paciente com id gerado automaticamente. */
    public Paciente registarPaciente(String nome, int idade) throws PacienteInvalidoException {
        Paciente paciente = new Paciente(proximoId, nome, idade);
        pacientes.put(paciente.getId(), paciente);
        proximoId++;
        return paciente;
    }

    /**
     * Classifica o paciente e encaminha-o para a fila correcta
     * (níveis 1–3 → FilaPrioridade, níveis 4–5 → Fila).
     */
    public Triagem realizarTriagem(Paciente paciente, String sintomas, NivelTriagem nivel)
            throws PacienteInvalidoException {
        Validacao.validarPacienteNaoNulo(paciente);
        if (pacientes.get(paciente.getId()) != paciente) {
            throw new PacienteInvalidoException(
                    "O paciente " + paciente.getNome() + " não está registado.");
        }
        if (paciente.isTriado()) {
            throw new PacienteInvalidoException(
                    "O paciente " + paciente.getNome() + " já foi triado.");
        }
        Triagem triagem = new Triagem(paciente, sintomas, nivel);
        encaminharPaciente(paciente);
        return triagem;
    }

    private void encaminharPaciente(Paciente paciente) {
        if (paciente.getNivel().isPrioritario()) {
            filaPrioridade.enqueue(paciente);
        } else {
            filaNormal.enqueue(paciente);
        }
    }

    // ---------- Atendimento ----------

    /** Próximo paciente a ser chamado, sem o remover da fila. */
    public Paciente proximoPaciente() throws FilaVaziaException {
        if (!filaPrioridade.isEmpty()) {
            return filaPrioridade.peek();
        }
        return filaNormal.peek();
    }

    /**
     * Retira o próximo paciente (prioritários primeiro) e inicia o seu atendimento.
     * O paciente só sai da fila se o atendimento puder ser criado.
     *
     * @throws IllegalStateException se já existir um atendimento em curso.
     */
    public Atendimento chamarProximo(String medico)
            throws FilaVaziaException, TriagemPendenteException, PacienteInvalidoException {
        if (atendimentoAtual != null) {
            throw new IllegalStateException("Já existe um atendimento em curso.");
        }
        Atendimento atendimento = new Atendimento(proximoPaciente(), medico);
        removerProximo();
        atendimentoAtual = atendimento;
        return atendimento;
    }

    private Paciente removerProximo() throws FilaVaziaException {
        if (!filaPrioridade.isEmpty()) {
            return filaPrioridade.dequeue();
        }
        return filaNormal.dequeue();
    }

    /**
     * Finaliza o atendimento em curso e guarda-o no histórico.
     *
     * @throws IllegalStateException se não existir atendimento em curso.
     */
    public Atendimento finalizarAtendimento(String observacoes) {
        if (atendimentoAtual == null) {
            throw new IllegalStateException("Não existe atendimento em curso.");
        }
        Atendimento atendimento = atendimentoAtual;
        atendimento.finalizar(observacoes);
        historico.add(atendimento);
        atendimentoAtual = null;
        return atendimento;
    }

    /** Atendimento em curso, ou null se não houver. */
    public Atendimento getAtendimentoAtual() {
        return atendimentoAtual;
    }

    // ---------- Consultas (cópias; alterá-las não afecta o gestor) ----------

    public Paciente buscarPaciente(int id) {
        return pacientes.get(id);
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes.values());
    }

    public List<Paciente> listarNaoTriados() {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente p : pacientes.values()) {
            if (!p.isTriado()) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Paciente> listarFilaPrioridade() {
        return filaPrioridade.toList();
    }

    public List<Paciente> listarFilaNormal() {
        return filaNormal.toList();
    }

    public List<Atendimento> getHistorico() {
        return new ArrayList<>(historico);
    }
}
