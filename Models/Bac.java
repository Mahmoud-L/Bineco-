import java.util.Dictionary;
import java.util.HashMap;

/**
Classe Bac : Du cote de Bineco, les infos d'un bac sont le code, nom et type que le resident lui accorde.
Les metriques du bac sont sauvegardes/mis a jours dans la bose de donnees de la municipalite
 */

public class Bac {

    private String code;
    private String nom;
    private String type;

    public Bac(String c, String n, String t){
        this.code=c;
        this.nom=n;
        this.type=t;
    }

    public String getCode() {return this.code;}
    public String getType() {return this.type;}
    public String getName() {return this.nom;}
}