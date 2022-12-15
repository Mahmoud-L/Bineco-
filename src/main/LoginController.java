package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
    User user;


    public User login() {
        String u;
        String p;

        System.out.println("Veuillez entrer votre nom d'utilisateur");
        u=reader.next();
        System.out.println("Veuillez entrer votre mot de passe");
        p=reader.next();
        this.user = (User) useRep.has(u,p);
        return this.user;

    }

    public User logout() {
        System.out.println("Vous avez choisi de vous déconnecter");
        try {
            useRep.storeRepo();
            resRep.storeRepo();
            conRep.storeRepo();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public User createUser() {
        String phoneNumber, email, address;
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
                useRep.storeRepo();

                System.out.println("Veuillez entrer votre prénom");
                String firstName = reader.next();

                System.out.println("Veuillez entrer votre nom de famille");
                String lastName = reader.next();

                System.out.println("Veuillez entrer votre adresse courriel");
                while(true) {
                    email=reader.next();
                    if (email.contains("@")) {
                        break;
                    } else {
                        System.out.println("SVP entrez une adresse courriel valide");
                    }
                }

                System.out.println("Veuillez entrer votre numero de telephone");
                while(true) {
                    phoneNumber=reader.next();
                    if (phoneNumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
                        break;
                    } else {
                        System.out.println("SVP entrez un numéro de téléphone valide");
                    }
                }

                System.out.println("Veuillez entrer votre adresse");
                address = reader.next();

                Resident res = new Resident(newRes.getId(), firstName, lastName, email, phoneNumber, address);
                resRep.add(res);
                resRep.storeRepo();
                return newRes;
            case 2:
                User newCon = new User(useRep.getNextId(), nomUtil, mdp, false);
                useRep.add(newCon);
                useRep.storeRepo();

                System.out.println("Veuillez entrer votre nom");
                String name = reader.next();

                System.out.println("Veuillez entrer votre code");
                String code = reader.next();

                System.out.println("Veuillez entrer votre e-mail");
                while(true) {
                    email=reader.next();
                    if (email.matches("/^\\S+@\\S+\\.\\S+$/")) {
                        break;
                    } else {
                        System.out.println("SVP entrez une adresse courriel valide");
                    }
                }

                System.out.println("Veuillez entrer votre numero de telephone");
                while(true) {
                    phoneNumber=reader.next();
                    if (phoneNumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
                        break;
                    } else {
                        System.out.println("SVP entrez un numéro de téléphone valide");
                    }
                }

                System.out.println("Veuillez entrer votre adresse");
                address = reader.next();

                System.out.println("Veuillez indiquer le type de consommateur que vous etes (EX : Recyclage, " +
                        "Compostage, Ordures)");
                String type = reader.next();

                System.out.println("Veuillez indiquer votre capacité");
                int capacity = reader.nextInt();

                System.out.println("Veuillez indiquer les activités que vous offrez (En format activite1,activite2,activite3...");
                String activities = reader.next();

                Consommateur con = new Consommateur(newCon.getId(),code,name, email,phoneNumber,address,type,capacity,activities);
                if (municipInfoService.validateConsom(code)){
                    conRep.add(con);
                    conRep.storeRepo();
                    return newCon;
                } else {
                    System.out.println("Vous n'êtes pas enregistré auprès de la municipalite.");
                }
        }
        return null;
    }

    public void changeUsername() {
        String userName;
        System.out.println("Veuillez entrer votre nouveau nom d'utilisateur");
        userName=reader.next();
        user.setUsername(userName);
        useRep.storeRepo();
    }

    public void changePassword() {
        String password;
        System.out.println("Veuillez entrer votre nouveau mot de passe");
        password=reader.next();
        user.setPassword(password);
        useRep.storeRepo();
    }

}