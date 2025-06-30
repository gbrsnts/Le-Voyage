package game.classes;

public class Inimigo extends Personagem {
    private int multiplicadorAtaque;
    private boolean atordoado;
    private int danoBonus;

    public Inimigo(String nome, int vida, int multiplicadorAtaque){
        super(nome, vida);
        this.multiplicadorAtaque = multiplicadorAtaque;
        this.atordoado = false;
        this.danoBonus = 0;
    }

    public void setAtordoado(boolean atordoado){
        this.atordoado = atordoado;
    }

    public boolean getAtordoado(){
        return this.atordoado;
    }

    public void setDanoBonus(int danoBonus){
        this.danoBonus = danoBonus;
    }

    public int getDanoBonus(){
        return this.danoBonus;
    }

    public int getMultiplicadorAtaque(){
        return this.multiplicadorAtaque;
    }
    
}
