package main;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Classe de controlleur de consommateur
 */

public class ConsomController extends Controller {

    static ConsomRepository conRep = new ConsomRepository();
    static ResidentRepository resRep = new ResidentRepository();
    Scanner reader = new Scanner(System.in);
    Consommateur consom;

    public ConsomController(String id){
        this.consom = (Consommateur) conRep.get(id);
    }

    public void notifyResids() {
        String msg;
        System.out.println("Veuillez entrer le message que vous souhaitez envoyer aux résidents");
        msg = reader.next();
        notifyResids(msg);
        System.out.println("Le message a été envoyé!");
    }

    public void notifyResids(String message) {
        for (int i=0; i<resRep.size(); i++){
            //Send email to each res
            continue;
        }
    }

    public void addActivity() {
        String activity;
        System.out.println("Veuillez entrer une courte description de l'activité");
        activity=reader.next();
        consom.addActivity(activity);
        this.notifyResids("Le consommateur " + consom.getName() + " a ajouté" +
                "l'activité suivante: " + activity);
        conRep.storeRepo();
    }

    public void editActivities() {
        int choix = -1, activity;
        System.out.println("Voici votre liste d'activités:");
        consom.displayActivities();
        System.out.println("Veuillez entrer le numéro de celle que vous souhaitez modifier");
        activity = reader.nextInt();
        System.out.println("Choisir une option : ");
        System.out.println("[0] Revenir en arrière");
        System.out.println("[1] Modifier l'activité");
        System.out.println("[2] Supprimer l'activité");
        while (true) {
            choix=reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de revenir en arrière");
                    break;
                case 1:
                    String newActivity;
                    System.out.println("Veuillez entrer la nouvelle description de cette activité");
                    newActivity = reader.next();
                    consom.editActivity(activity, newActivity);
                    this.notifyResids("Le consommateur " + consom.getName() + " a modifié sa liste d'activités");
                    System.out.println("L'activité a été modifiée");
                    conRep.storeRepo();
                    break;
                case 2:
                    consom.deleteActivity(activity);
                    this.notifyResids("Le consommateur " + consom.getName() + " a modifié sa liste d'activités");
                    System.out.println("L'activité a été supprimée");
                    conRep.storeRepo();
                    break;
                default :
                    System.out.println("Veuillez choisir uniquement un des 3 choix proposés");
            }
        }
    }

    public void viewActivities() {
        consom.displayActivitiesWithGrades();
    }

    public void changeName() {
        String name;
        System.out.println("Veuillez entrer votre nouveau nom");
        name=reader.next();
        consom.setName(name);
        conRep.storeRepo();
    }

    public void changeEmail() {
        String email;
        System.out.println("Veuillez entrer votre nouvelle adresse courriel");
        while(true) {
            email=reader.next();
            if (email.contains("@")) {
                consom.setEmail(email);
                conRep.storeRepo();
                break;
            } else {
                System.out.println("SVP entrez une adresse courriel valide");
            }
        }
    }

    public void changePhoneNumber() {
        String phoneNumber;
        System.out.println("Veuillez entrer votre nouveau numéro de téléphone");
        while(true) {
            phoneNumber=reader.next();
            if (phoneNumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
                consom.setPhoneNumber(phoneNumber);
                conRep.storeRepo();
                break;
            } else {
                System.out.println("SVP entrez un numéro de téléphone valide");
            }
        }
    }

    public void changeAddress() {
        String address;
        System.out.println("Veuillez entrer votre nouvelle adresse");
        address=reader.next();
        consom.setAddress(address);
        conRep.storeRepo();
    }

    public void changeType() {
        String type;
        System.out.println("Veuillez entrer votre nouveau type");
        type=reader.next();
        consom.setType(type);
        conRep.storeRepo();
    }

    public void changeCapacity() {
        int quantity;
        System.out.println("Veuillez entrer votre nouvelle quantité");
        quantity=reader.nextInt();
        consom.setCapacity(quantity);
        conRep.storeRepo();
    }

}