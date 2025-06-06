package game.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Heroi extends Personagem {
    private ArrayList<String> fasesRestantes;
    private int rodadas;

    public Heroi(String nome, int vida){
        super(nome, vida);
        this.fasesRestantes = new ArrayList<>(Arrays.asList("Floresta", "Caverna", "Vila"));
        this.rodadas = 1;
    }

    public ArrayList<String> getFasesRestantes(){
        return this.fasesRestantes;
    }

    public void removeFase(String fase){
        this.fasesRestantes.remove(fase);
    }

    public void incrementarRodadas(){
        this.rodadas++;
    }

    public int getRodadas(){
        return this.rodadas;
    }

    public void resetarRodadas(){
        this.rodadas = 1;
    }

    public void adicionarVida(int quantidade){
        this.vida += quantidade;
        restaurarVida();
    }

    public void restaurarVida(){
        this.vida_atual = this.vida;
    }
}
