package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe de controlleur de resident
 */

public class ResidentController extends Controller {

    static ResidentRepository resRep = new ResidentRepository();
    MunicipInfoService municipInfo = new MunicipInfoService();
    ConsomRepository conRep = new ConsomRepository();
    Scanner reader = new Scanner(System.in);

    Resident res;

    /**
     *lier l'objet resident avec le resident connecte actuellement
     * @param id
     */
    public ResidentController(String id){
        this.res=(Resident) resRep.get(id);
    }

    /**
     * Ajoute un bac avec son code Qr, son nom et son type
     */
    public void addBac() {
        System.out.println("Veuillez entrez le code QR du bac");
        String c = reader.next();
        System.out.println("Veuillez entrez le nom du bac");
        String n = reader.next();
        System.out.println("Veuillez entrez le type du bac");
        String t = reader.next();
        res.addBac(new Bac(c, n, t));
        resRep.storeRepo();
    }

    /**
     * Supprimer un bac a partir de la liste des bacs du residents
     */
    public boolean deleteBac() {
        ArrayList<Bac> bacs = res.getBacs();
        if (bacs.size() > 0) {
            int bacToDelete;
            int i = 0;
            System.out.println("Veuillez entrer le code du bac a supprimer :");
            for (Bac bac : bacs) {
                System.out.println("[" + i + "] " + "Nom: "+ bac.getName());
                i++;
            }
            while (true) {
                bacToDelete = reader.nextInt();
                if (bacToDelete >= 0 && bacToDelete < bacs.size()) {
                    System.out.println("---Bac Supprimé---");
                    resRep.storeRepo();
                    return res.deleteBac(bacToDelete);
                }
                System.out.println("Svp veuillez entrer un numéro de bac valide");
            }

        } else {
            System.out.println("Vous n'avez aucun bac à votre compte");
            return false;
        }

    }


    /**
     *Afficher les bacs d'un residents avec leurs Code Qr ,le nom ,le type ,le niveau de remplissage et
     * sa proportion
     */
    public void viewBacs() {
        ArrayList<Bac> bacs = res.getBacs();
        for (Bac bac : bacs) {
            System.out.println("Code QR: "+bac.getCode());
            System.out.println("Nom: "+bac.getName());
            System.out.println("Type: "+bac.getCode());
            System.out.println("Niveau de remplissage: "+municipInfo.getBacRemp(bac.getCode()));
            System.out.println("Proportions: "+municipInfo.getBacProp(bac.getCode()).toString());
            System.out.println("------------------------------");
        }
        if (bacs.size()==0) {System.out.println("Vous n'avez aucun bac");}
    }


    /**
     * Calcul du facteur PNU ,du facteur PNU et du score ECOLO et affichage de ces 3 metriques .
     */
    public void viewMetrics() {
        if (this.res.getBacs().size() == 0) {
            System.out.println("Comme vous n'avez pas de bac, nous ne pouvons pas calculer vos métriques");
        } else {
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
    }

    /**
     * Modifier les metriques en selectionnant R C O ou CIM
     */
    public boolean setMetrics() {
        HashMap<String, Double> metricParams = res.getMetricParams();
        System.out.println("Vos parametres de métriques actuelles sont (Poids de chaque categorie dans le calcul et Cout ideal max des activites:");
        System.out.println(metricParams.toString());
        System.out.println("Veuillez selectionner la colonne a modifier en entrant R,C,O ou CIM:");
        String choix=reader.next();
        double newVal;
        switch (choix){
            case "R":
                System.out.println("Veuillez entrer la nouvelle valeur du poids de recyclage (entre 0,0 et 1,0)");
                while (true) {
                    newVal=reader.nextDouble();
                    if (newVal >= 0 && newVal <= 1) {
                        res.editMetricParam("Recyclage",newVal);
                        break;
                    } else {
                        System.out.println("Veuillez entrer une valeur entre 0,0 et 1,0 SVP");
                    }
                }
                break;
            case "C":
                System.out.println("Veuillez entrer la nouvelle valeur du poids de compostage (entre 0,0 et 1,0)");
                while (true) {
                    newVal=reader.nextDouble();
                    if (newVal >= 0 && newVal <= 1) {
                        res.editMetricParam("Compostage",newVal);
                        break;
                    } else {
                        System.out.println("Veuillez entrer une valeur entre 0,0 et 1,0 SVP");
                    }
                }
                break;
            case "O":
                System.out.println("Veuillez entrer la nouvelle valeur du poids de ordures (entre 0,0 et 1,0)");
                while (true) {
                    newVal=reader.nextDouble();
                    if (newVal >= 0 && newVal <= 1) {
                        res.editMetricParam("Ordures",newVal);
                        break;
                    } else {
                        System.out.println("Veuillez entrer une valeur entre 0,0 et 1,0 SVP");
                    }
                }
                break;
            case "CIM":
                System.out.println("Veuillez entrer la nouvelle valeur du cout idéal max des activités (entre 0,0 et 100,0)");
                while (true) {
                    newVal=reader.nextDouble();
                    if (newVal >= 0 && newVal <= 100) {
                        res.editMetricParam("CIM",newVal);
                        break;
                    } else {
                        System.out.println("Veuillez entrer une valeur entre 0,0 et 100,0 SVP");
                    }
                }
                break;
            default :
                System.out.println("La colonne entrée est invalide");
        }
        resRep.storeRepo();
        return true;
    }


    /**
     *Afficher l'etat du lot donnee par MunicipInfo.
     */
    public void viewMunicipState() {
        municipInfo.getLotState();
    }

    /**
     * Signaler un probleme ecris par l'utilisateur
     */

    public boolean reportProb() {
        System.out.println("Veuillez entrer une breve description de votre probleme:");
        String msg=reader.next();
        municipInfo.reportProb(res.getReportInfos()+","+msg);
        return true;
    }


    /**
     * Trouver un consommateur en utilisant par nom ou bien par filtage de type : R C ou O .
     */
    public void findConsom() {
        System.out.println("Pour filtrer par categories, veuillez entrer R,C, ou O (pour recyclage, compostage ou ordures)");
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
        searchResult.get(choix).displayInfos();
        System.out.println("Voulez-vous noter ce consommateur ? [Y/N]");
        String rep = reader.next();
        if (Objects.equals(rep, "Y")){
            rateConsom(searchResult.get(choix).getId());
        }
    }

    /**
     * Donner une note a un consommateur
     * @param id
     */
    public void rateConsom(String id) {
        Consommateur cons = (Consommateur) conRep.get(id);
        ArrayList<String> acts = cons.getActivities();
        int note;
        for (int i=0; i<acts.size(); i++){
            System.out.println(acts.get(i)+ ": Veuillez entrer une note entre 1 et 5");
            while (true) {
                note = reader.nextInt();
                if (note >= 1 && note <= 5) {
                    cons.addNote(note,i);
                    break;
                } else {
                    System.out.println("Veuillez entrer une note entre 1 et 5 SVP");
                }
            }
        }
        conRep.storeRepo();
        System.out.println("---Merci pour votre feedback---");
    }

    /**
     * Changer le nom et prenom du resident deja connecte .
     */
    public void changeName() {
        String firstName, lastName;
        System.out.println("Veuillez entrer votre nouveau prénom");
        firstName=reader.next();
        System.out.println("Veuillez entrer votre nouveau nom");
        lastName=reader.next();
        res.setFirstName(firstName);
        res.setLastName(lastName);
        resRep.storeRepo();
    }

    /**
     * Changer l'email du resident deja connecte.
     */

    public void changeEmail() {
        String email;
        System.out.println("Veuillez entrer votre nouvelle adresse courriel");
        while(true) {
            email=reader.next();
            if (email.contains("@")) {
                res.setEmail(email);
                resRep.storeRepo();
                break;
            } else {
                System.out.println("SVP entrez une adresse courriel valide");
            }
        }
    }

    /**
     * Changer le numero du telephone du resident deja connecte .
     */
    public void changePhoneNumber() {
        String phoneNumber;
        System.out.println("Veuillez entrer votre nouveau numéro de téléphone");
        while(true) {
            phoneNumber=reader.next();
            if (phoneNumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
                res.setPhoneNumber(phoneNumber);
                resRep.storeRepo();
                break;
            } else {
                System.out.println("SVP entrez un numéro de téléphone valide");
            }
        }

    }


    /**
     * Changer l'adresse de l'utilisateur deja connecte.
     */
    public void changeAddress() {
        String address;
        System.out.println("Veuillez entrer votre nouvelle adresse");
        address=reader.next();
        res.setAddress(address);
        resRep.storeRepo();
    }

}