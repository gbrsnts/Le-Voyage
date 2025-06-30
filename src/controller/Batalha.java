package controller;

import model.Heroi;
import model.Inimigo;

public class Batalha {
    public static Boolean iniciarBatalha(Heroi heroi, Inimigo inimigo){
        heroi.gerar_segredo();
        inimigo.gerar_segredo();

        int turno = 1;
        while (heroi.vivo() && inimigo.vivo()) {
            if (turno % 2 == 1) {
                boolean terminouTurno = Acoes.turnoHeroi(heroi, inimigo);
                if (terminouTurno) {
                turno++;
                }
            } else {
                Acoes.turnoInimigo(heroi, inimigo);
                turno++;
                heroi.incrementarRodadas();
            }
        }
        return Acoes.concluirBatalha(heroi);
    }
}
