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

    public static void menuPrincipal(){
        Scanner input = new Scanner(System.in);
        System.out.print("Qual o nome do seu personagem? ");
        String nome = input.nextLine();
        while (true){    
            Heroi heroi = new Heroi(nome, 5);
            int proximaFase = 0;

            executandoFaseIndice(heroi, proximaFase);
            proximaFase++;
            
            while (true){
                if (!heroi.vivo()){
                    if (!finalizarJogo(heroi)){
                        return; // encerra o jogo
                    } else {
                        break; // reinicia todo o menuPrincipal
                    }
                }

                System.out.println("--- Menu principal ---");
                System.out.println("[1] Próxima batalha");
                System.out.println("[2] Status de " + heroi.getNome());
                System.out.println("[3] Acessar inventário");
                System.out.println("[0] Fechar o jogo");
                System.out.print("Escolha uma opção: ");

                try{
                    int escolha = Integer.parseInt(input.nextLine());
                    switch (escolha) {
                        case 1:
                            if (proximaFase < 5) {
                                executandoFaseIndice(heroi, proximaFase);
                                proximaFase++;
                            } else {
                                if (!finalizarJogo(heroi)) {
                                    return;
                                } else {
                                    break;
                                }
                            }
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
                            return;
                        default:
                            Util.limparTerminal();
                            System.out.println("Opção inválida.");

                    }
                } catch (NumberFormatException e) {
                    System.out.println("Somente números são permitidos.");
                }
            }
        }
    }

    public static void executandoFaseIndice(Heroi heroi, int fase){
        switch (fase){
            case 0:
                executarFase(heroi, "Floresta do desespero", new Inimigo("Monstrengo", 3 ,1), "Pergaminho perdido");
                break;
            case 1:
                executarFase(heroi, "Caverna sangrenta", new Inimigo("Urso Sangrento", 5, 2), "Lança perfurante");
                break;
            case 2:
                executarFase(heroi, "Torre amaldiçoada", new Inimigo("Golem de pedra", 10, 3), "Escudo protetor");
            case 3:
                executarFase(heroi, "Pântan maldito", new Inimigo("Mago", 20, 5), "Espada mágica");
            case 4:
                executarFase(heroi, "Castelo fantasmagórico", new Inimigo("João Trovão", 50, 10), "Panela enfeitiçada");
            default:
                break;
        }  
    }

    public static void executarFase(Heroi heroi, String nomeTerritorio, Inimigo inimigo, String recompensa) {
        if (!heroi.vivo()) return;

        Territorio territorio = new Territorio(nomeTerritorio, inimigo, recompensa);
        territorio.iniciarBatalha();
        territorio.batalhar(inimigo, heroi);
    }

    public static boolean finalizarJogo(Heroi heroi){
        Scanner input = new Scanner(System.in);

        if (heroi.vivo()){
            System.out.println("--- PARABÉNS ---");
            System.out.println(heroi.getNome() + " concluiu todas as fases!");
        } else {
            System.out.println("--- FIM DE JOGO ---");
        }
        
        System.out.println("[1] Jogar novamente");
        System.out.println("[2] Sair do jogo");

        while (true) {
            System.out.print("Escolha uma opção: ");
            try {
                int escolha = Integer.parseInt(input.nextLine());
                switch (escolha) {
                    case 1:
                        heroi.resetar();
                        return true;
                    case 2:
                        System.out.println("Obrigado por jogar!");
                        return false;
                    default:    
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Somente números são permitidos.");
            }
        }
}

    
}
