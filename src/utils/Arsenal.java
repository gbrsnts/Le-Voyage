package utils;

import java.util.HashMap;

import model.Artefato;
import model.enums.ArtefatoTipo;

public class Arsenal {
    public static HashMap<String, Artefato> carregarArtefatos(){
        HashMap<String, Artefato> artefatos = new HashMap<>();

        artefatos.put("Pergaminho perdido", new Artefato("Pergaminho perdido", ArtefatoTipo.PERGAMINHO_PERDIDO));
        artefatos.put("Lança perfurante", new Artefato("Lança perfurante", ArtefatoTipo.LANCA_PERFURANTE));
        return artefatos;
    }
}
