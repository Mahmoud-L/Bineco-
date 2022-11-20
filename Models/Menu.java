import java.util.*;
public class Menu {
    static LoginController logCon = new LoginController();
    static ResidentController resCon;
    static ConsomController consCon ;
    private User loggedUser = null;
    private int indexPage = 0;

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
                        displayMain();
                    }
                    else {
                        displayMainCons();
                    }
                }
                break;
                case 2 :
                    loggedUser = logCon.createUser();
                    System.out.println("Votre compte a été crée.");
                    System.out.println("Veillez maintenent vous conneter");
                    choix = 1 ;
                    break;
                case 0 :
                    System.out.println("Vous avez choisi de quitter l'application ");
                    break;
                default :
                    System.out.println("Veillez choisir uniquement un des 3 choix proposés");
            }
        }
    }
    public void displayMainCons() {
        consCon = new ConsomController(loggedUser.getId()) ;
        Scanner reader = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {
            System.out.println(" Choisir une option : ");
            System.out.println("[1] Notifier les residents ");
            System.out.println("[0] Quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ");
                    break;
                case 1:
                    consCon.notifyResids();
                    break;
            }
        }
    }
    public void displayMain() {
        resCon = new ResidentController(loggedUser.getId());
        Scanner reader = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Mes bacs");
            System.out.println("[2] Municipalité");
            System.out.println("[3] Metriques");
            System.out.println("[4] Consommateur");
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
                default:
                    System.out.println("Veillez choisir uniquement un des 4 choix proposés");

            }
        }
    }

    public void displayBacPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Ajouter mes bacs");
            System.out.println("[2] Supprimer un bac");
            System.out.println("[3] Voir mes bacs");
            System.out.println("[0] quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce Menu ");
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
                    System.out.println("Veillez choisir uniquement un des 4 choix proposés");
            }
        }
    }

    public void displayMetriquesPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Voir les metriques");
            System.out.println("[2] Configurer les metriques ");
            System.out.println("[0] quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce Menu ");
                    break;
                case 1:
                    resCon.viewMetrics();
                    break;
                case 2:
                    resCon.setMetrics();
                    break;
                default :
                    System.out.println("Veillez choisir uniquement un des 3 choix proposés");
            }
        }
    }

    public void displayMunicipPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Voir l'etat de mes déchets ");
            System.out.println("[2] Signaler un Problème");
            System.out.println("[0] quitter");
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
                    System.out.println("Veillez choisir uniquement un des 3 choix proposés");
            }
        }
    }

    public void displayConsomPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while (choix != 0) {
            System.out.println("Choisir une option : ");
            System.out.println("[1] Trouver un consommateur");
            System.out.println("[0] quitter");
            choix = reader.nextInt();
            switch (choix) {
                case 0:
                    System.out.println("Vous avez choisi de quitter ce Menu ");
                    break;
                case 1:
                    resCon.findConsom();
                    break;
                default :
                    System.out.println("Veillez choisir uniquement un des 2 choix proposés");
            }
        }
    }

    /**
     *
     * @param msg
     */

}