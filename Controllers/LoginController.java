import java.util.Scanner;

public class LoginController extends Controller {


    static UserRepository useRep = new UserRepository();
    static ResidentRepository resRep = new ResidentRepository();
    static ConsomRepository conRep = new ConsomRepository();
    Scanner reader = new Scanner(System.in);

    public LoginController(){
        useRep.add(new User("1","Mokh","Mahmoud",true));
    }


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
        System.out.println("Vous avez choisi de quitter l'application ");
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
                System.out.println("Veuillez indiquer le type de consommateur que vous etes");
                String type = reader.next();
                System.out.println("Veuillez indiquer votre capacité ");
                int capacity = reader.nextInt();
                System.out.println("Veuillez indiquer les activités que vous offrez");
                String activities = reader.next();// a verifier
                Consommateur con = new Consommateur(newCon.getId(),code,e_mail,phoneNumber,addresCons,type,capacity,activities);
                conRep.add(con);
                return newCon;
        }
        return null;
    }
}