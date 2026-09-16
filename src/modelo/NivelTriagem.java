package src.modelo;

public enum NivelTriagem {
    EMERGENCIA(1, "Vermelho"),
    MUITO_URGENTE(2, "Laranja"),
    URGENTE (3, "Amarelo"),
    NORMAL (4, "Verde"),
    NAO_URGENTE(5, "Azul");

    private final String descricao;
    private final int nivel;
    private final String cor;

    NivelTriagem( int nivel, String cor) {
       this.descricao = "";
       this.nivel= nivel;
       this.cor= cor; 
    }

    public String getDescricao() {
        return descricao;
    }
    
    public int getNivel() {
        return nivel;
    }

    public String getCor() {
        return cor;
    }

    @Override 
    public String toString() {
        return descricao + "(" + nivel + "," + cor + ")";
    }
}
