import java.util.*;

/**
 * MunicipInfoService
 */
public class MunicipInfoService {

    Bac[] repBac = new Bac[3];
    HashMap<String, Double> cost = new HashMap<String, Double>();
    HashMap<String, Double> useRecyc = new HashMap<String, Double>();
    HashMap<String, Double> useCompo = new HashMap<String, Double>();

    public MunicipInfoService(){
        Bac bac1= new Bac("A","2150 Rue Mackay", new Date(), "Recyclage", .5);
        Bac bac2= new Bac("B","364 Ch cote des neiges", new Date(), "Compostage", .2);
        Bac bac3= new Bac("C","15 Av linton", new Date(), "Ordures", .8);
        bac1.prop.put("Cartons",.2);
        bac1.prop.put("Verre",.3);
        bac1.prop.put("Plastique",.5);
        bac2.prop.put("Fruits/legumes", .7);
        bac2.prop.put("Viandes",.3);
        repBac[0]=bac1;
        repBac[1]=bac2;
        repBac[2]=bac3;

        cost.put("Cr",5.0);
        cost.put("Cc",5.0);
        cost.put("Co",5.0);

        useRecyc.put("Cartons", .4);
        useRecyc.put("Verre", .2);
        useRecyc.put("Plastique", .1);
        useRecyc.put("Metal", .3);

        useCompo.put("Fruits/legumes",.4);
        useCompo.put("Viandes",.4);
        useCompo.put("Laitier",.2);
    }


    public boolean validateConsommateur(String code) {
        // Appel MunicipInfo: get-consommateur(code)
        Consomateur consomateur = getConsommateur(code);

        return consomateur.code == code;
    }

    public boolean validateBac(String codeqr, String adresse) {
        // Appel MunicipInfo: get-consommateur(code)
        Bac bac = getBac(codeqr);

        return bac.codeqr == codeqr;
    }

    public Double getBacRemp(String codeqr){
        return getBac(codeqr).remp;
    }
    public HashMap<String, Double> getBacProp(String codeqr){
        return getBac(codeqr).prop;
    }


    private List<Consomateur> listConsommateur() {
        var consommateurList = new ArrayList<Consomateur>();

        return consommateurList;
    }

    /**
     * Cette fonction simule l'appel à get-consommateur(code)
     * @param code
     * @return {@link Consomateur}
     */
    private Consomateur getConsommateur(String code) {
        var consomateur = new Consomateur();

        consomateur.code = "232";
        consomateur.nom = "replastic";
        consomateur.adresse = "";
        consomateur.email = "info@replastic.com";
        consomateur.telephone = "";
        consomateur.details = "Nous transformons tout vos plastiques et verres jetées en bouteille prêt à être utilisé pour conserveau votre eau et vos brevages favoris";
        consomateur.type_dechets = new String[] { "plastique", "verre" };
        consomateur.capacite = "100 tonnes";

        return consomateur;
    }

    /**
     * Cette fonction simule l'appel à get-bac(codeqr)
     * @param codeqr
     * @return {@link Bac}
     */
    private Bac getBac(String codeqr) {
        for (Bac b: repBac){
            if (Objects.equals(b.codeqr, codeqr)) {return b;}
        }

        return null;
    }

    /**
     * Cette fonction simule l'appel à list-lot()
     * @return
     */
    private List<Lot> listLot() {
        var lotList = new ArrayList<Lot>();

        return lotList;
    }

    /**
     * Cette fonction simule l'appel à get-lot(numero)
     * @param numero
     * @return
     */
    private Lot getLot(String numero) {
        var lot = new Lot();

        return lot;
    }

    /**
     * Cette fonction simule l'appel à post-message()
     * @return
     */
    private boolean postMessage() {
        return true;
    }

    private class Consomateur {
        String code;
        String nom;
        String adresse;
        String email;
        String telephone;
        String details;
        String[] type_dechets;
        String capacite;
    }

    private class Bac {
        String codeqr;
        String adresse;
        Date dateEmission;
        String type;
        double remp;
        HashMap<String, Double> prop = new HashMap<String, Double>();
        public Bac(String c, String a, Date d, String t, double r){
            this.codeqr=c;
            this.adresse=a;
            this.dateEmission=d;
            this.type=t;
            this.remp=r;
        }
    }

    private class Lot {
        String numero;
        String type;
        String parent;
        Lot[] sous_lots;
        Date dateRamassage;
        Date dateLivraison;
        String statut;
        int qte_total;
        int qte_traite;
        float taux_rejet;
        float cout;
        Consomateur[] consomateurs;
        String activites;
    }
}