package modelo;

public class Paciente {
    private int id;
    private String nome;
    private int idade;
    private NivelTriagem nivel;
    private long horaChegada;
    private EstadoAtendimento estado;
    private String sintomas;
    private boolean triado;

    public Paciente(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.horaChegada = System.currentTimeMillis();
        this.triado = false;
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

    public boolean isTriado() {
        return triado;
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

    /**
     * Retorna o valor numérico da prioridade.
     * Caso o paciente ainda não tenha sido triado, retorna 5 (menor prioridade por
     * omissão).
     */
    public int getPrioridade() {
        return (nivel != null) ? nivel.getNivel() : 5;
    }

    // Setters
    public void setNivel(NivelTriagem nivel) {
        this.nivel = nivel;
        this.triado = true;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setTriado(boolean triado) {
        this.triado = triado;
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