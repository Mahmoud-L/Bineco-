package main;

import java.util.*;
/**
Classe du menu Bineco
 */

public class Menu {
    static LoginController logCon = new LoginController();
    static ResidentController resCon;
    static ConsomController consCon ;
    private User loggedUser = null;

    //Menu de connection
    public void displayLoginPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while ( choix != 0) {
            System.out.println("Choisir une option :");
            System.out.println("[1] Se connecter");
            System.out.println("[2] S'inscrire");
            System.out.println("[0] Quitter ");
            choix = reader.nextInt();
            switch (choix){
                case 1 :
                    loggedUser = logCon.login();
                if (loggedUser != null) {
                    if (loggedUser.isRes()) {
                        displayMainRes();
                    }
                    else {
                        displayMainCons();
                    }
                }
                break;
                case 2 :
                    loggedUser = logCon.createUser();
                    System.out.println("Votre compte a été créé.");
                    System.out.println("Veuillez maintenent vous connecter");
                    choix = 1 ;
                    break;
                case 0 :
                    loggedUser= logCon.logout();
                    break;
                default :
                    System.out.println("Veuillez choisir uniquement un des 3 choix proposés");
            }
        }
    }

    //*****  RESIDENT  *****//

    //menu principal du resident
    public void displayMainRes() {
        resCon = new ResidentController(loggedUser.getId());
        Scanner reader = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Mes bacs");
            System.out.println("[2] Municipalité");
            System.out.println("[3] Metriques");
            System.out.println("[4] Consommateur");
            System.out.println("[5] Modifier mon compte");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter l'application");
                    break;
                case 1:
                    displayBacPage();
                    break;
                case 2:
                    displayMunicipPage();
                    break;
                case 3:
                    displayMetriquesPage();
                    break;
                case 4:
                    displayConsomPage();
                    break;
                case 5:
                    displayResEditAccount();
                    break;
                default:
                    System.out.println("Veuillez choisir uniquement un des 6 choix proposés");

            }
        }
    }

    //menu de gestions des bacs
    public void displayBacPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Ajouter un bac");
            System.out.println("[2] Supprimer un bac");
            System.out.println("[3] Voir mes bacs");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce main.Menu ");
                    break;
                case 1:
                    resCon.addBac();
                    break;
                case 2:
                    resCon.deleteBac();
                    break;
                case 3:
                    resCon.viewBacs();
                    break;
                default :
                    System.out.println("Veuillez choisir uniquement un des 4 choix proposés");
            }
        }
    }

    //Menu de la municipalite
    public void displayMunicipPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Voir l'etat de mes déchets ");
            System.out.println("[2] Signaler un Problème");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce Menu  ");
                    break;
                case 1:
                    resCon.viewMunicipState();
                    break;
                case 2:
                    resCon.reportProb();
                    break;
                default :
                    System.out.println("Veuillez choisir uniquement un des 3 choix proposés");
            }
        }
    }

    //menu de gestions des metriques
    public void displayMetriquesPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Voir les metriques");
            System.out.println("[2] Configurer les metriques ");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce main.Menu ");
                    break;
                case 1:
                    resCon.viewMetrics();
                    break;
                case 2:
                    resCon.setMetrics();
                    break;
                default :
                    System.out.println("Veuillez choisir uniquement un des 3 choix proposés");
            }
        }
    }

    //Menu de gestions des consommateurs
    public void displayConsomPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Liste des consommateurs");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce Menu ");
                    break;
                case 1:
                    resCon.findConsom();
                    break;
                default :
                    System.out.println("Veuillez choisir uniquement un des 2 choix proposés");
            }
        }
    }
    public void displayResEditAccount() {
        Scanner reader = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Modifier nom d'utilisateur");
            System.out.println("[2] Modifier mot de passe");
            System.out.println("[3] Modifier nom");
            System.out.println("[4] Modifier email");
            System.out.println("[5] Modifier numéro de téléphone");
            System.out.println("[6] Modifier adresse");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce Menu  ");
                    break;
                case 1:
                    logCon.changeUsername();
                    break;
                case 2:
                    logCon.changePassword();
                    break;
                case 3:
                    resCon.changeName();
                    break;
                case 4:
                    resCon.changeEmail();
                    break;
                case 5:
                    resCon.changePhoneNumber();
                    break;
                case 6:
                    resCon.changeAddress();
                    break;
                default:
                    System.out.println("Veuillez choisir uniquement un des 7 choix proposés");
            }
        }
    }


    //***** CONSOMMATEUR *****//

    //Menu principal du consommateur
    public void displayMainCons() {
        consCon = new ConsomController(loggedUser.getId()) ;
        Scanner reader = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {
            System.out.println(" Choisir une option : ");
            System.out.println("[1] Notifier les residents ");
            System.out.println("[2] Voir mes activités");
            System.out.println("[3] Modifier mon compte");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ");
                    break;
                case 1:
                    consCon.notifyResids();
                    break;
                case 2:
                    displayActivities();
                    break;
                case 3:
                    displayConsEditAccount();
                    break;
                default:
                    System.out.println("Veuillez choisir uniquement un des 4 choix proposés");
            }
        }
    }

    public void displayActivities() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Ajouter une activité");
            System.out.println("[2] Supprimer une activité");
            System.out.println("[3] Voir mes activités");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce menu ");
                    break;
                case 1:
                    consCon.addActivity();
                    break;
                case 2:
                    consCon.editActivities();
                    break;
                case 3:
                    consCon.viewActivities();
                    break;
                default :
                    System.out.println("Veuillez choisir uniquement un des 4 choix proposés");
            }
        }
    }

    public void displayConsEditAccount() {
        Scanner reader = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Modifier nom d'utilisateur");
            System.out.println("[2] Modifier mot de passe");
            System.out.println("[3] Modifier nom");
            System.out.println("[4] Modifier email");
            System.out.println("[5] Modifier numéro de téléphone");
            System.out.println("[6] Modifier adresse");
            System.out.println("[7] Modifier type");
            System.out.println("[8] Modifier quantité");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce menu  ");
                    break;
                case 1:
                    logCon.changeUsername();
                    break;
                case 2:
                    logCon.changePassword();
                    break;
                case 3:
                    consCon.changeName();
                    break;
                case 4:
                    consCon.changeEmail();
                    break;
                case 5:
                    consCon.changePhoneNumber();
                    break;
                case 6:
                    consCon.changeAddress();
                    break;
                case 7:
                    consCon.changeType();
                    break;
                case 8:
                    consCon.changeCapacity();
                    break;
                default:
                    System.out.println("Veuillez choisir uniquement un des 9 choix proposés");
            }
        }
    }
}