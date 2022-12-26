package main;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.HashMap;


/**
 * Cette classe sert a affecter les infos d'un bac c.a.d le code, nom et type que le resident lui accorde.
 * Les metriques du bac sont sauvegardes/mis a jours dans la bose de donnees de la municipalite.
 */


public class Bac implements Serializable {

    private String code;
    private String nom;
    private String type;

    /**
     * fonction qui affecte le code, le nom et le type d'un bac au bac lui meme.
     */

    public Bac(String c, String n, String t){
        this.code=c;
        this.nom=n;
        this.type=t;
    }


    /**
     * fonction qui sert a avoir le code QR du bac
     * @return le code QR du bac
     */
    public String getCode() {return this.code;}
    /**
     * fonction qui sert a avoir le type du bac
     * @return le type du bac
     */
    public String getType() {return this.type;}
    /**
     * fonction qui sert a avoir le nom du bac
     * @return le nom du bac
     */
    public String getName() {return this.nom;}
}