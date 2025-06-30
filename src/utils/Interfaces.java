package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Artefato;
import model.Heroi;
import model.Inimigo;
import model.Territorio;


public class Interfaces {
    public static String menuBatalha(Heroi heroi){
        Scanner input = new Scanner(System.in);
        HashMap<Integer, String> opcoes = new HashMap<>();
        
        int escolha = -1;
            while (true) {
                System.out.println("-----");
                int indice = 1;
                System.out.println("[" + indice + "] Atacar com a espada");
                opcoes.put(indice, "atacar");
                indice++;

                System.out.println("[" + indice + "] Status da batalha");
                opcoes.put(indice, "status");
                indice++;

                List<Artefato> meusArtefatos = heroi.getArtefatosObtidos();
                if (!meusArtefatos.isEmpty()){
                    System.out.println("[" + indice + "] Acessar inventário");
                    opcoes.put(indice, "inventario");
                    indice++;
                }

                try {
                    System.out.print("Qual ação deseja fazer? ");
                    escolha = input.nextInt();
                    if (opcoes.containsKey(escolha)) {
                        return opcoes.get(escolha);
                    } else {
                        Util.limparTerminal();
                        System.out.println("Opção inválida.");
                    }
                } catch (Exception e) {
                    Util.limparTerminal();
                    System.out.println("Somente números são permitidos.");
                    input.nextLine();
                }
            }
    }

    public static void statusBatalha(Heroi heroi, Inimigo inimigo){
        Util.limparTerminal();
        System.out.println("--- Status ---");
        System.out.println(heroi.getNome() + " está com " + heroi.getVida() + " pontos de vida.");
        if (inimigo != null){
            System.out.println(inimigo.getNome() + " está com " + inimigo.getVida() + " pontos de vida.");
        }
    }

    public static void exibirInventario(Heroi heroi){
        Scanner input = new Scanner(System.in);
        List<Artefato> meusArtefatos = heroi.getArtefatosObtidos();
        Util.limparTerminal();
        System.out.println("--- Inventário ---");
        int sair = meusArtefatos.size() + 1;
        if (!meusArtefatos.isEmpty()){
            int indice = 1;
            for (Artefato a : meusArtefatos) {
                String status = a.getAtivo() ? "(Ativo)" : "(Inativo)";
                System.out.println(indice + " - " + a.getNome() + " " + status);
                indice++;
            }
        } else {
            System.out.println("Você ainda não possui nenhum item para ser exibido.");
        }
        System.out.println(sair + " - Sair");
        try {
            System.out.print("Qual ação deseja fazer? ");
            int acao_item = input.nextInt();
            if (acao_item >= 1 && acao_item <= meusArtefatos.size()){
                Artefato selecionado = meusArtefatos.get(acao_item - 1);
                heroi.ativarArtefato(selecionado.getNome());
                System.out.println(selecionado.getNome() + " foi " + (selecionado.getAtivo() ? "ativado." : "desativado."));
            } else if (acao_item == sair){
                return;
            }
            else{
                System.out.println("Opção inválida.");
            }
            
        } catch (Exception e) {
            System.out.println("Opção inválida.");
        }
        
    }

    public static void menuPrincipal(Heroi heroi){
        Scanner input = new Scanner(System.in);
        boolean jogo = true;
        
        while (jogo){
            System.out.println("--- Menu principal ---");
            System.out.println("[1] Ir para batalha");
            System.out.println("[2] Status de " + heroi.getNome());
            System.out.println("[3] Acessar inventário");
            System.out.println("[0] Fechar o jogo");
            System.out.print("Escolha uma opção: ");

            try{
                int escolha = Integer.parseInt(input.nextLine());
                switch (escolha) {
                    case 1:
                        Util.limparTerminal();
                        fluxoFases(heroi);
                        break;
                    case 2:
                        Util.limparTerminal();
                        statusBatalha(heroi, null);
                        break;
                    case 3:
                    Util.limparTerminal();
                        exibirInventario(heroi);
                        break;
                    case 0:
                        System.out.println("Obrigado por jogar!");
                        jogo = false;
                        break;
                    default:
                        Util.limparTerminal();
                        System.out.println("Opção inválida.");

                }
            } catch (NumberFormatException e) {
                System.out.println("Somente números são permitidos.");
            }
        }
    }

    public static void fluxoFases(Heroi heroi){
        // Fase Floresta
        Inimigo monstro = new Inimigo("Monstrengo", 3, 1);
        Territorio floresta = new Territorio("Floresta do desespero", monstro, "Pergaminho perdido");
        floresta.iniciarBatalha();
        floresta.batalhar(monstro, heroi);

        // Fase caverna
        Inimigo urso = new Inimigo("Urso sangrento", 5, 3);
        Territorio caverna = new Territorio("Caverna sangrenta", urso, "Lança perfurante");
        caverna.iniciarBatalha();
        caverna.batalhar(urso, heroi);

    }
}
