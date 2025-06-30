package model.enums;

public enum ArtefatoTipo {
    PERGAMINHO_PERDIDO(2, 0),
    LANCA_PERFURANTE(3, 2),
    ESCUDO_PROTETOR(0, -1),
    ESPADA_MAGICA(10, 3),
    PANELA_ENFEITICADA(1000, 0);

    private final int efeito;
    private final int consequencia;

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
