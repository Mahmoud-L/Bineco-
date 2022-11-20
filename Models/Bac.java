import java.util.Dictionary;
import java.util.HashMap;

public class Bac {

    private String code;
    private String nom;
    private String type;

    public Bac(String c, String n, String t){
        this.code=c;
        this.nom=n;
        this.type=t;
    }

    public String getCode() {
        return this.code;
    }
    public String getType() { return this.type;}

}