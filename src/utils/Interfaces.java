package utils;

import game.classes.Heroi;
import game.classes.Inimigo;
import java.util.HashMap;
import java.util.Scanner;

public class Interfaces {
    public static String menuBatalha(Heroi heroi){
        Scanner input = new Scanner(System.in);
        HashMap<Integer, String> opcoes = new HashMap<>();
        int indice = 1;
        System.out.println("[" + indice + "] Atacar com a espada");
        opcoes.put(indice, "atacar");
        indice++;

        System.out.println("[" + indice + "] Status da batalha");
        opcoes.put(indice, "status");
        indice++;

        
        try {
            int escolha = input.nextInt();
            return opcoes.get(escolha);
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            return null;
        }
    }

    public static void statusBatalha(Heroi heroi, Inimigo inimigo){
        System.out.println(heroi.getNome() + " está com " + heroi.getVida() + " pontos de vida.");
        if (inimigo != null){
            System.out.println(inimigo.getNome() + " está com " + inimigo.getVida() + " pontos de vida.");
        }
    }
}
