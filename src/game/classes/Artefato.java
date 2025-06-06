package game.classes;

public class Artefato {
    private String nome;
    private boolean possui;
    private boolean ativo;
    private int efeito;
    private int consequencia;
    
    public Artefato(String nome){
        this.nome = nome;
        this.possui = false;
        this.ativo = false;
        this.efeito = 0;
        this.consequencia = 0;
    }
}
