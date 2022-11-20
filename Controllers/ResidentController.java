import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ResidentController extends Controller {

    static ResidentRepository resRep = new ResidentRepository();
    MunicipInfoService municipInfo = new MunicipInfoService();
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
        String code = "omek kahba";
        return res.deleteBac(code);
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
        return true;
    }

    public void viewMunicipState() {
        // TODO - implement ResidentController.viewMunicipState
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @param text
     */
    public boolean reportProb()
    {
        return true;
    }

    public void findConsom() {
        // TODO - implement ResidentController.findConsom
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public void rateConsom(String id) {

    }

}