package model;

import controller.Batalha;

public class Territorio {
    private String nome;
    private Inimigo inimigo;
    private String artefato;

    public Territorio(String nome, Inimigo inimigo, String artefato){
        this.nome = nome;
        this.inimigo = inimigo;
        this.artefato = artefato;
    }

    public void iniciarBatalha(){
        System.out.println("Iniciando batalha em " + nome + " contra " + inimigo.getNome() + "!");
    }

    public void batalhar(Inimigo inimigo, Heroi heroi){
        Boolean vitoria = Batalha.iniciarBatalha(heroi, inimigo);
        if (vitoria){
            System.out.println(heroi.getNome() + " derrotou o " + inimigo.getNome() + ".");
            heroi.coletarArtefato(this.artefato);
        } else {
            System.out.println("Você foi derrotado pelo " + inimigo.getNome() + ".");
        }
    }
}
