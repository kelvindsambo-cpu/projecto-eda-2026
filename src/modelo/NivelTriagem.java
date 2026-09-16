package modelo;

public enum NivelTriagem {
    EMERGENCIA(1, "Emergência", "Vermelho"),
    MUITO_URGENTE(2, "Muito Urgente", "Laranja"),
    URGENTE(3, "Urgente", "Amarelo"),
    NORMAL(4, "Normal", "Verde"),
    NAO_URGENTE(5, "Não Urgente", "Azul");

    private final int nivel;
    private final String categoria;
    private final String cor;

    NivelTriagem(int nivel, String categoria, String cor) {
        this.nivel = nivel;
        this.categoria = categoria;
        this.cor = cor;
    }

    public int getNivel() {
        return nivel;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCor() {
        return cor;
    }

    public boolean isPrioritario() {
        return this.nivel <= 3;
    }

    @Override
    public String toString() {
        return nivel + " - " + categoria + " (" + cor + ")";
    }
}