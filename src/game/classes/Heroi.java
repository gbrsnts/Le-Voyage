package game.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import utils.Arsenal;

public class Heroi extends Personagem {
    private ArrayList<String> fasesRestantes;
    private HashMap<String, Artefato> artefatos;
    private int rodadas;

    public Heroi(String nome, int vida){
        super(nome, vida);
        this.fasesRestantes = new ArrayList<>(Arrays.asList("Floresta", "Caverna", "Vila"));
        this.rodadas = 1;
        this.artefatos = Arsenal.carregarArtefatos();
    }

    public ArrayList<String> getFasesRestantes(){
        return this.fasesRestantes;
    }

    public HashMap<String, Artefato> getArtefatos(){
        return this.artefatos;
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

    public void coletarArtefato(String nome) {
        Artefato artefato = this.artefatos.get(nome);
        if (artefato.getPossui() == false && artefato != null) {
            artefato.setPossui(true);
        }
    }

    public List<Artefato> getArtefatosObtidos(){
        List<Artefato> obtidos = new ArrayList<>();
        for (Map.Entry<String, Artefato> entry : artefatos.entrySet()){
            Artefato artefato = entry.getValue();
            if (artefato.getPossui()) {
                obtidos.add(artefato);
            }
        }
        return obtidos;
    }

    public void ativarArtefato(String nome) {
        Artefato artefato = this.artefatos.get(nome);
        if (artefato != null && artefato.getPossui()) {
            artefato.setAtivo(!artefato.getAtivo());
        }
    }

    public int calcularAtaqueAdicional(){
        int adicional = 1;
        for (Artefato artefato : this.artefatos.values()) {
            if (artefato.getPossui() && artefato.getAtivo()) {
                adicional += artefato.getEfeito();
            }
        }
        return adicional;
    }

    public int calcularAdicionalInimigo(){
        int bonus = 0;
        for (Artefato artefato : this.artefatos.values()) {
            if (artefato.getPossui() && artefato.getAtivo()) {
                bonus += artefato.getConsequencia();
            }
        }
        return bonus;
    }
}
