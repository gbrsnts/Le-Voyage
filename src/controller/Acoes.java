package controller;

import java.util.Random;

import model.Artefato;
import model.Heroi;
import model.Inimigo;
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
        int adicional = calcularAtaqueAdicional(heroi);
        int segredo = inimigo.getSegredo();
        int ocorrencias = 0;

        for (int i = 1; i <=  adicional; i++){
            int ataque = rand.nextInt(heroi.getVidaTotal()) + 1;
            if (ataque == segredo){
                ocorrencias++;
            }
        }

        if (ocorrencias > 0) {
            int dano = (int) ocorrencias * segredo;
            inimigo.receberDano(dano);
            System.out.println(heroi.getNome() + " atacou e infligiu " + dano + " de dano em " + inimigo.getNome() + ".");
            if (inimigo.vivo() && ocorrencias > 1){
                inimigo.setAtordoado(true);
                System.out.println("O inimigo foi atordoado devido um ataque crítico e ficará um turno sem atacar.");
            }
        } else{
            System.out.println(inimigo.getNome() + " se esquivou do ataque do " + heroi.getNome() + ".");
        }
    }

    public static void ataqueInimigo(Heroi heroi, Inimigo inimigo){
        Random rand = new Random();
        int segredo = heroi.getSegredo();
        int multiplicador = inimigo.getMultiplicadorAtaque();
        int ocorrencias = 0;
        for (int i = 1; i <= multiplicador; i++){
            int ataque = rand.nextInt(heroi.getVidaTotal()) + 1;
            if (ataque == segredo) {
                ocorrencias++;
            }
        }
        
        if (ocorrencias > 0){
            calcularAdicionalInimigo(heroi, inimigo);
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

    public static void calcularAdicionalInimigo(Heroi heroi, Inimigo inimigo) {
        int bonus = 0;
        for (Artefato artefato : heroi.getArtefatos().values()) {
            if (artefato.getPossui() && artefato.getAtivo()) {
                bonus += artefato.getConsequencia();
            }
        }
        inimigo.setDanoBonus(bonus);
    }

    public static int calcularAtaqueAdicional(Heroi heroi){
        int adicional = 1;
        for (Artefato artefato : heroi.getArtefatos().values()) {
            if (artefato.getPossui() && artefato.getAtivo()) {
                adicional += artefato.getEfeito();
            }
        }
        return adicional;
    }
}

