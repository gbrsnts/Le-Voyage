import java.util.Scanner;

import game.classes.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Qual o seu nome: ");
        String nome = input.nextLine();
        
        Heroi heroi = new Heroi(nome, 5);
        Inimigo monstro = new Inimigo("Monstrengo", 3, 1);
        Territorio floresta = new Territorio("Floresta do desespero", monstro, "Pergaminho perdido");
        floresta.iniciarBatalha();
        floresta.batalhar(monstro, heroi);
        input.close();

    }
}
