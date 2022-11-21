import java.util.Scanner;
/**
 * Classe de controlleur de login (utilisateur)
 */

public class LoginController extends Controller {


    static UserRepository useRep = new UserRepository();
    static ResidentRepository resRep = new ResidentRepository();
    static ConsomRepository conRep = new ConsomRepository();
    MunicipInfoService municipInfoService = new MunicipInfoService();
    Scanner reader = new Scanner(System.in);


    public User login() {
        String u;
        String p;

        System.out.println("Veuillez entrer votre nom d'utilisateur");
        u=reader.next();
        System.out.println("Veuillez entrer votre mot de passe");
        p=reader.next();
        return (User) useRep.has(u,p);

    }


    public User logout() {
        System.out.println("Vous avez choisi de vous deconnecter");
        return null;
    }

    public User createUser() {
        String nomUtil;
        String mdp;
        int resOuCon;
        System.out.println("Veuillez entrer votre nom d'utilisateur");
        nomUtil = reader.next();
        System.out.println("Veuillez entrer votre mot de passe");
        mdp = reader.next();
        System.out.println("Choisir une des deux options suivantes");
        System.out.println("[1] Resident");
        System.out.println("[2] Consommateur");
        resOuCon = reader.nextInt();
        switch (resOuCon) {
            case 1:
                User newRes = new User(useRep.getNextId(), nomUtil, mdp, true);
                useRep.add(newRes);
                System.out.println("Veuillez entrer votre prénom");
                String fn = reader.next();
                System.out.println("Veuillez entrer votre nom de famille");
                String ln = reader.next();
                System.out.println("Veuillez entrer votre e-mail");
                String email = reader.next();
                System.out.println("Veuillez entrer votre numero de telephone");
                String num = reader.next();
                System.out.println("Veuillez entrer votre adresse");
                String address = reader.next();
                Resident res = new Resident(newRes.getId(), fn, ln, email, num, address);
                resRep.add(res);
                return newRes;
            case 2:
                User newCon = new User(useRep.getNextId(), nomUtil, mdp, false);
                useRep.add(newCon);
                System.out.println("Veuillez entrer votre nom");
                String name = reader.next();
                System.out.println("Veuillez entrer votre code");
                String code = reader.next();
                System.out.println("Veuillez entrer votre e-mail");
                String e_mail = reader.next();
                System.out.println("Veuillez entrer votre numero de telephone");
                String phoneNumber = reader.next();
                System.out.println("Veuillez entrer votre adresse");
                String addresCons = reader.next();
                System.out.println("Veuillez indiquer le type de consommateur que vous etes (EX : Recyclage," +
                        "Compostage,Ordures)");
                String type = reader.next();
                System.out.println("Veuillez indiquer votre capacité ");
                int capacity = reader.nextInt();
                System.out.println("Veuillez indiquer les activités que vous offrez (En format activite1,activite2,activite3...");
                String activities = reader.next();
                Consommateur con = new Consommateur(newCon.getId(),code,name, e_mail,phoneNumber,addresCons,type,capacity,activities);
                if (municipInfoService.validateConsom(name)){
                    conRep.add(con);
                    return newCon;
                } else {
                    System.out.println("Vous n'etes pas enregistre au pres de la municipalite.");
                }
        }
        return null;
    }
}