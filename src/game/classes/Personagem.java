package game.classes;
import java.util.Random;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int segredo;
    protected int vida_atual;

    public Personagem(String nome, int vida){
        this.nome = nome;
        this.vida = vida;
        this.vida_atual = vida;
    }

    public void gerar_segredo(){
        Random random = new Random();
        this.segredo = random.nextInt(this.vida) + 1;
    }

    public int getSegredo(){
        return this.segredo;
    }

    public boolean vivo(){
        return this.vida > 0;
    }

    public void receberDano(int dano){
        this.vida_atual -= dano;
    }

    public String getNome(){
        return this.nome;
    }

    public int getVida(){
        return this.vida_atual;
    }

    public int getVidaTotal(){
        return this.vida_atual;
    }
    
}
