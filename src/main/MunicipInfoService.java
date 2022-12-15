package main;

import java.util.*;

/**
 Service MunicipInfo
 On assume que ce service externe nous fournit les parametres non-configurables pour le calcul des metriques, le
 repertoire des bacs de la municipalite ainsi que leurs metriques, les consommateurs enregistres aupres de
 la municipalite
 */
public class MunicipInfoService {

    Bac[] repBac = new Bac[6];
    Consommateur[] repCons = new Consommateur[10];
    HashMap<String, Double> cost = new HashMap<String, Double>();
    HashMap<String, Double> useRecyc = new HashMap<String, Double>();
    HashMap<String, Double> useCompo = new HashMap<String, Double>();

    public MunicipInfoService(){
        Bac bac1= new Bac("A","2150 Rue Mackay", new Date(), "Recyclage", .5);
        Bac bac2= new Bac("B","364 Ch cote des neiges", new Date(), "Compostage", .2);
        Bac bac3= new Bac("C","15 Av linton", new Date(), "Ordures", .8);
        Bac bac4= new Bac("D","34 Rue Mackay", new Date(), "Recyclage", .6);
        Bac bac5= new Bac("E","79 Ch cote des neiges", new Date(), "Compostage", .3);
        Bac bac6= new Bac("F","25 Av linton", new Date(), "Ordures", .9);
        bac1.prop.put("Cartons",.2);
        bac1.prop.put("Verre",.3);
        bac1.prop.put("Plastique",.5);
        bac2.prop.put("Fruits/legumes", .7);
        bac2.prop.put("Viandes",.3);
        bac4.prop.put("Cartons",.4);
        bac4.prop.put("Verre",.5);
        bac4.prop.put("Plastique",.1);
        bac5.prop.put("Fruits/legumes", .5);
        bac5.prop.put("Viandes",.5);
        repBac[0]=bac1;
        repBac[1]=bac2;
        repBac[2]=bac3;
        repBac[3]=bac4;
        repBac[4]=bac5;
        repBac[5]=bac6;

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

        Consommateur con1 = new Consommateur("213", "cons1", "conso1@yahoo.fr",
                "123-546-789", "5450 blabla , Montreal", "Recyclage,Ordure", 550,"" +
                "cartons, brulerie d'ordures" );
        Consommateur con2 = new Consommateur("216", "cons2", "conso2@yahoo.fr",
                "153-546-000", "5451 blabla , Montreal","Recyclage,Compostage,Ordures", 900,
                "bouteilles en verre,engrais de plante,export d'ordures" );
        Consommateur con3 = new Consommateur("21", "cons3", "conso3@yahoo.fr",
                "163-006-789", "5456 blabla , Montreal",  "Recyclage",520,"fabrication de " +
                "cahiers et livres");
        Consommateur con4 = new Consommateur("13", "cons4", "conso4@yahoo.fr",
                "123-540-789", "5440 blabla , Montreal", "Compostage", 600, "extraction de" +
                "calcium et fers");
        Consommateur con5 = new Consommateur("2163", "cons5", "conso5@yahoo.fr",
                "123-506-789", "5490 blabla , Montreal", "Recyclage", 500,"sac en plastique," +
                "bouteilles en plastiques");
        Consommateur con6 = new Consommateur("21003", "cons6", "conso6@yahoo.fr",
                "123-546-780", "8450 blabla , Montreal", "Compostage,Ordure",454,"engrais" +
                "pour plantes,brulerie d'ordures" );
        Consommateur con7 = new Consommateur("23413", "cons7", "conso7@yahoo.fr",
                "123-546-709", "6910 blabla , Montreal", "Ordure",456,"brulerie d'ordures" );
        Consommateur con8 = new Consommateur("2163", "cons8", "conso8@yahoo.fr",
                "123-545-729", "5400 blabla , Montreal", "Compostage",456,"engrais pour plantes");
        Consommateur con9 = new Consommateur( "213410", "cons9", "conso9@yahoo.fr",
                "123-547-789", "5004 blabla , Montreal", "Recyclage,Ordure",456,"barre de" +
                "metal,brulerie d'ordures");
        Consommateur con10 = new Consommateur("3", "cons10", "conso10@yahoo.fr",
                "103-546-789", "5455 blabla , Montreal",  "Recyclage,Ordure",6,"bouteilles" +
                " en verres, cartons");
        repCons[0]=con1;
        repCons[1]=con2;
        repCons[2]=con3;
        repCons[3]=con4;
        repCons[4]=con5;
        repCons[5]=con6;
        repCons[6]=con7;
        repCons[7]=con8;
        repCons[8]=con9;
        repCons[9]=con10;
    }



    public Double getBacRemp(String codeqr){
        return getBac(codeqr).remp;
    }
    public HashMap<String, Double> getBacProp(String codeqr){
        return getBac(codeqr).prop;
    }

    public void getLotState(){
        Lot lot = this.getLot("1");
        lot.type="Recyclage";
        lot.parent="EcoCentre";
        lot.sous_lots= new Lot[0];
        lot.dateRamassage= new Date("01/11/2022");
        lot.dateLivraison= new Date("10/11/2022");
        lot.statut= "En traitement";
        lot.qte_total=250;
        lot.qte_traite=150;
        lot.taux_rejet=20;
        lot.cout=300;
        lot.consomateurs= new Consommateur[3];
        lot.consomateurs[0] = repCons[1];
        lot.consomateurs[1] = repCons[4];
        lot.consomateurs[2] = repCons[7];
        lot.activites="Bouteilles de verre, Cartons";
        System.out.println("Date de ramassage: "+lot.dateRamassage.toString());
        System.out.println("Date de livraison: "+lot.dateLivraison.toString());
        System.out.println("Consommateurs: "+ Arrays.toString(lot.consomateurs));
        System.out.println("Activitees maintenues: "+lot.activites);
        System.out.println("Statut du lot: "+lot.statut);
        System.out.println("Quantite total (en KG): "+lot.qte_total);
        System.out.println("Quantite traitee (en KG): "+lot.qte_traite);
    }

    public void reportProb(String infos){
        //traitement de la requete
        //on peut utiliser split "," pour obtenir les infos
        System.out.println("Votre report a ete envoye.");
    }

    public boolean validateConsom(String code){
        for (Consommateur cons : repCons){
            if (Objects.equals(cons.code, code)) {return true;}
        }
        return false;
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
     * Cette fonction simule l'appel à get-lot(numero)
     * @param numero
     * @return
     */
    private Lot getLot(String numero) {
        var lot = new Lot();

        return lot;
    }


    private class Consommateur {
        String code;
        String nom;
        String adresse;
        String email;
        String telephone;
        String type_dechets;
        int capacite;
        public Consommateur(String c, String n, String e, String t, String a, String ty, int cap, String act){
            this.code=c;
            this.nom=n;
            this.adresse=a;
            this.email=e;
            this.telephone=t;
            this.type_dechets=ty;
            this.capacite=cap;
        }
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
        Consommateur[] consomateurs;
        String activites;
    }
}