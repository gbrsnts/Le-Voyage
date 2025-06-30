package view;

import model.Heroi;
import utils.Interfaces;

public class Main {
    public static void main(String[] args) throws Exception {
        Heroi heroi = new Heroi("Sanduba", 5);
        Interfaces.menuPrincipal(heroi);
    }
}
