package game.enums;

public enum ArtefatoTipo {
    PERGAMINHO_PERDIDO(2, 0),
    LANCA_PERFURANTE(3, 2);

    private final int efeito;
    private int consequencia;

    ArtefatoTipo(int efeito, int consequencia){
        this.efeito = efeito;
        this.consequencia = consequencia;
    }
    
    public int getEfeito(){
        return efeito;
    }

    public int getConsequencia(){
        return consequencia;
    }
}
