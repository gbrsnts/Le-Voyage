package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import game.classes.Heroi;
import game.classes.Inimigo;
import utils.Interfaces;
import utils.Util;

public class Acoes {
    public static Boolean concluirBatalha(Heroi heroi){
        if (heroi.vivo()){
            // Adicionar 2 pontos de vida ao herói como recompensa
            heroi.adicionarVida(2);
            // Restar o contador de rodadas da batalha
            heroi.resetarRodadas();
            return true;
        } else {
            return false;
        }
    }

    public static void ataqueHeroi(Heroi heroi, Inimigo inimigo){
        Util.limparTerminal();
        System.out.println("--- Rodada "+ heroi.getRodadas() + " --");
        Random rand = new Random();
        int ataque = rand.nextInt(inimigo.getVida()) + 1;
        if (ataque == inimigo.getSegredo()){
            inimigo.receberDano(ataque);
            System.out.println(heroi.getNome() + " atacou e infligiu " + ataque + " de dano em " + inimigo.getNome() + ".");
        } else{
            System.out.println(inimigo.getNome() + " se esquivou do ataque do " + heroi.getNome() + ".");
        }
    }

    public static void ataqueInimigo(Heroi heroi, Inimigo inimigo){
        ArrayList<Integer> listaAtaque = new ArrayList<>();
        int multiplicador = inimigo.getMultiplicadorAtaque();
        Random rand = new Random();
        for (int i = 1; i <= multiplicador; i++){
            int ataque = rand.nextInt(heroi.getVida()) + 1;
            listaAtaque.add(ataque);
        }
        int segredo = heroi.getSegredo();
        if (listaAtaque.contains(segredo)){
            int ocorrencias = Collections.frequency(listaAtaque, segredo);
            int dano = (ocorrencias * segredo) + inimigo.getDanoBonus();
            heroi.receberDano(dano);
            System.out.println(inimigo.getNome() + " atacou e infligiu " + dano + " de dano em " + heroi.getNome() + ".");
        } else {
            System.out.println(heroi.getNome() + " se esquivou do ataque do " + inimigo.getNome() + ".");
        }
    }
    
    public static Boolean turnoHeroi(Heroi heroi, Inimigo inimigo){
        inimigo.setDanoBonus(0);
        String acao = Interfaces.menuBatalha(heroi);
        if (acao.equalsIgnoreCase("atacar")){
            ataqueHeroi(heroi, inimigo);
            return true;
        } else if (acao.equalsIgnoreCase("status")){
            Interfaces.statusBatalha(heroi, inimigo);    
        } else if (acao.equalsIgnoreCase("inventario")){
            Interfaces.exibirInventario(heroi);
        }
        return false;
    }

    public static void turnoInimigo(Heroi heroi, Inimigo inimigo){
        if (inimigo.getAtordoado()){
            System.out.println(inimigo.getNome() + "está atordoado e perdeu seu turno.");
        } else {
            ataqueInimigo(heroi, inimigo);
        }
        inimigo.setAtordoado(false);
    }
}
