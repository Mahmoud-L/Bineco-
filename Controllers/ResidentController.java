import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ResidentController extends Controller {

    static ResidentRepository resRep = new ResidentRepository();
    MunicipInfoService municipInfo = new MunicipInfoService();
    ConsomRepository conRep = new ConsomRepository();
    Scanner reader = new Scanner(System.in);

    Resident res;

    public ResidentController(String id){
        resRep.add(new Resident("1","mokh","mahmoud","asba","omek","zebi"));
        this.res=(Resident) resRep.get(id);
    }

    public void addBac() {
        System.out.println("Veuillez entrez le code QR du bac");
        String c = reader.next();
        System.out.println("Veuillez entrez le nom du bac");
        String n = reader.next();
        System.out.println("Veuillez entrez le type du bac");
        String t = reader.next();
        res.addBac(new Bac(c, n, t));
    }


    public boolean deleteBac() {
        System.out.println("Veuillez entrer le code du bac a supprimer :");
        String bacToDelete = reader.next();
        System.out.println("---Bac Supprime---");
        return res.deleteBac(bacToDelete);
        //Catch exception return false
    }


    public void viewBacs() {
        ArrayList<Bac> bacs = res.getBacs();
        for (Bac bac : bacs) {
            System.out.println(bac);
        }
        if (bacs.size()==0) {System.out.println("Vous n'avez aucun bac");}
    }

    public void viewMetrics() {
        HashMap<String, Double> metricParams = res.getMetricParams();

        //Calcul de PN
        double PNr = 0;
        double PNc = 0;
        double PNo = 0;
        double PNrI = 0;
        double PNcI = 0;
        double PNoI = 0;
        int rCount = 0;
        int cCount = 0;
        int oCount = 0;

        double NrSum =0;
        double NcSum =0;
        double NoSum =0;
        double PN;
        for (Bac b : res.getBacs()){
            String t = b.getType();
            double remp = municipInfo.getBacRemp(b.getCode());
            double poids = metricParams.get(t);
            if (Objects.equals(t, "Recyclage")){
                PNr+=remp*poids;
                PNrI+=1*poids;
                rCount+=1;

                NrSum+=remp;
            }
            if (Objects.equals(t, "Compostage")){
                PNc+=remp*poids;
                PNcI+=1*poids;
                cCount+=1;

                NcSum+=remp;
            }
            if (Objects.equals(t, "Ordures")){
                PNo+=remp*poids;
                oCount+=1;

                NoSum+=remp;
            }
        }
        PNr/=rCount;
        PNc/=cCount;
        PNo/=oCount;
        PNrI/=rCount;
        PNcI/=cCount;
        PN = (PNr+PNc)/(PNo+1);

        NrSum/=rCount;
        NcSum/=cCount;
        NoSum/=oCount;
        System.out.println("Le facteur PN (positif/negatif) est : "+ PN);

        //Calcul de PNU
        double Ur = 0;
        double Uc = 0;
        double maxEr=0;
        double maxEc=0;
        double PNU;
        double PNUI;
        for (Bac b : res.getBacs()) {
            String t = b.getType();
            HashMap<String, Double> prop = municipInfo.getBacProp(b.getCode());
            if (Objects.equals(t, "Recyclage")){
                HashMap<String, Double> poids = municipInfo.useRecyc;
                for (String key: prop.keySet()){
                    Ur+=prop.get(key)*poids.get(key);
                    maxEr=Double.max(prop.get(key),maxEr);
                }
            }
            if (Objects.equals(t, "Compostage")){
                HashMap<String, Double> poids = municipInfo.useCompo;
                for (String key: prop.keySet()){
                    Uc+=prop.get(key)*poids.get(key);
                    maxEc=Double.max(prop.get(key),maxEc);
                }
            }
        }
        Ur/=maxEr;
        Uc/=maxEc;
        PNU = Math.log((PNr*Ur + PNc*Uc + 1) / (PNo + 1));
        PNUI = Math.log((PNrI*Ur + PNcI*Uc + 1) / (PNoI + 1));
        System.out.println("Le facteur PNU (positif/negatif) est : "+ PNU);
        System.out.println("Le facteur PNU (positif/negatif) est : "+PNUI);
        System.out.println("Le score ECOLO est : "+ PNU/PNUI);

        //Calcul de CN
        HashMap<String, Double> couts = municipInfo.cost;
        double Cr=couts.get("Cr");
        double Cc=couts.get("Cc");
        double Co=couts.get("Co");
        double CIM = metricParams.get("CIM");
        double CN = (NrSum*Cr + NcSum*Cc + NoSum*Co - CIM) / CIM;
        System.out.println("Le score ECONO est : "+ (1 - Double.max(0, CN)));


    }

    public boolean setMetrics() {
        HashMap<String, Double> metricParams = res.getMetricParams();
        System.out.println("Vos parametres de métriques actuelles sont (Poids de chaque categorie dans le calcul et Cout ideal max des activites:");
        System.out.println(metricParams.toString());
        System.out.println("Veuillez selectionner la colonne a modifier en entrant R,C,O ou CIM:");
        String choix=reader.next();
        double newVal;
        switch (choix){
            case "R":
                System.out.println("Veuillez entrer la nouvelle valeur du poids de recyclage (entre 0 et 1)");
                newVal=reader.nextDouble();
                res.editMetricParam("Recyclage",newVal);
                break;
            case "C":
                System.out.println("Veuillez entrer la nouvelle valeur du poids de compostage (entre 0 et 1)");
                newVal=reader.nextDouble();
                res.editMetricParam("Compostage",newVal);
                break;
            case "O":
                System.out.println("Veuillez entrer la nouvelle valeur du poids de ordures (entre 0 et 1)");
                newVal=reader.nextDouble();
                res.editMetricParam("Ordures",newVal);
                break;
            case "CIM":
                System.out.println("Veuillez entrer la nouvelle valeur du cout idéal max des activités (entre 0 et 100)");
                newVal=reader.nextDouble();
                res.editMetricParam("CIM",newVal);
                break;

        }
        return true;
    }

    public void viewMunicipState() {
        municipInfo.getLotState();
    }

    public boolean reportProb()
    {
        System.out.println("Veuillez entrer une breve description de votre probleme:");
        String msg=reader.next();
        municipInfo.reportProb(res.getReportInfos()+","+msg);
        return true;
    }

    public void findConsom() {
        System.out.println("Pour filtrer par categories, veuillez entrer R,C, ou O (pour recyclage, compostage ou orudes.");
        System.out.println("Pour chercher par nom, veuillez entrer une partie du nom du consommateur");
        String search = reader.next();
        ArrayList<Consommateur> searchResult;
        switch (search){
            case "R":
                searchResult = conRep.filterC("Recyclage");
                break;
            case "C":
                searchResult= conRep.filterC("Compostage");
                break;
            case "O":
                searchResult= conRep.filterC("Ordures");
                break;
            default:
                searchResult= conRep.filterN(search);
                break;
        }
        for (int i=0; i<searchResult.size(); i++){
            System.out.println("["+i+"] : "+searchResult.get(i).getName());
        }
        System.out.println("Veuillez faire votre choix en entrant un chiffre");
        int choix = reader.nextInt();
        searchResult.get(choix).getInfos();
        System.out.println("Est ce que vous voulez noter ce consommateur ? [Y/N]");
        String rep = reader.next();
        if (Objects.equals(rep, "Y")){
            rateConsom(searchResult.get(choix).getId());
        }
    }

    /**
     *
     * @param id
     */
    public void rateConsom(String id) {
        Consommateur cons = (Consommateur) conRep.get(id);
        String[] acts = cons.getActivities();
        cons.incCount();
        int note;
        for (int i=0; i<acts.length; i++){
            System.out.println(acts[i]+ ": Veuillez entrer une note entre 1 et 5");
            note = reader.nextInt();
            cons.addNote(note,i);
        }
        System.out.println("---Merci pour votre feedback");
    }

}