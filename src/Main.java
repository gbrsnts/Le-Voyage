import java.util.Scanner;

import game.classes.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        //System.out.print("Qual o seu nome: ");
        //String nome = input.nextLine();
        
        Heroi heroi = new Heroi("Sanduba", 5);

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

        input.close();

    }
}
