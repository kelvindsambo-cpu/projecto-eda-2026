package modelo;

import excecoes.PacienteInvalidoException;
import excecoes.TriagemPendenteException;
import util.Validacao;

public class Paciente {
    private int id;
    private String nome;
    private int idade;
    private NivelTriagem nivel;
    // Só para consulta/estatística; a ordem nas filas é a ordem de inserção.
    private long horaChegada;
    private EstadoAtendimento estado;
    private String sintomas;

    public Paciente(int id, String nome, int idade) throws PacienteInvalidoException {
        Validacao.validarPaciente(nome, idade);

        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.horaChegada = System.currentTimeMillis();
        this.estado = EstadoAtendimento.AGUARDANDO;
        this.sintomas = "Sintomas não registados";
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public long getHoraChegada() {
        return horaChegada;
    }

    /** Um paciente está triado quando já tem um nível atribuído. */
    public boolean isTriado() {
        return nivel != null;
    }

    public NivelTriagem getNivel() {
        return nivel;
    }

    public EstadoAtendimento getEstado() {
        return estado;
    }

    public String getSintomas() {
        return sintomas;
    }

    public int getPrioridade() throws TriagemPendenteException {
        if (!isTriado()) {
            throw new TriagemPendenteException(
                    "O paciente " + nome + " ainda não foi triado.");
        }

        return nivel.getNivel();
    }

    // Setters
    public void setNivel(NivelTriagem nivel) {
        this.nivel = nivel;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setEstado(EstadoAtendimento estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String nivelStr = (nivel != null) ? nivel.getCategoria() : "Pendente";
        return String.format("ID: %d | Nome: %s | Categoria: %s | Estado: %s",
                id, nome, nivelStr, estado);
    }
}