package game.classes;

import game.enums.ArtefatoTipo;

public class Artefato {
    private String nome;
    private boolean possui;
    private boolean ativo;
    private ArtefatoTipo tipo;
    
    public Artefato(String nome, ArtefatoTipo tipo){
        this.nome = nome;
        this.tipo = tipo;
        this.possui = false;
        this.ativo = false;
    }

    public void setPossui(boolean possui) {
        this.possui = possui;
    }

    public boolean getPossui(){
        return this.possui;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean getAtivo(){
        return this.ativo;
    }

    public ArtefatoTipo getTipo(){
        return this.tipo;
    }

    public int getEfeito(){
        return tipo.getEfeito();
    }

    public String getNome(){
        return this.nome;
    }

}
