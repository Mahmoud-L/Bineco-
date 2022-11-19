import com.sun.source.tree.WhileLoopTree;

import java.util.*;
public class Menu {
    static LoginController logCon = new LoginController();

    private User loggedUser = null;
    private int indexPage = 0;

    public void open() {
        // TODO - implement Menu.open
        throw new UnsupportedOperationException();
    }

    public void displayLoginPage() {
        Scanner reader = new Scanner(System.in);
        int choix  = -1 ;
        while ( choix != 0) {
            System.out.print("Choisir une option : ");
            System.out.println("[1] Se connecter ");
            System.out.println("[2] S'inscrire");
            System.out.println("[0] Quitter ");
            choix = reader.nextInt();
            if (choix == 1) {
                loggedUser = logCon.login() ;
                if (loggedUser != null) {
                    displayMain();
                }
            } else if (choix == 2) {
                loggedUser = logCon.createUser();
                System.out.println("Votre compte a été crée.");
                System.out.println("Vous pouvez maintenent choisir l'option se connecter ");
            }
            else if(choix == 0) {
                System.out.println("Vous avez choisi de quitter l'application ");
            }
            else {
                System.out.println("Veillez choisir uniquement un des 3 choix proposés");
            }
        }
    }
    public void displayMain() {
        System.out.println("omek kahba");

    }

    public void displayBacPage() {
        // TODO - implement Menu.displayBacPage
        throw new UnsupportedOperationException();
    }

    public void displayMetriquesPage() {
        // TODO - implement Menu.displayMetriquesPage
        throw new UnsupportedOperationException();
    }

    public void displayMunicipPage() {
        // TODO - implement Menu.displayMunicipPage
        throw new UnsupportedOperationException();
    }

    public void displayConsomPage() {
        // TODO - implement Menu.displayConsomPage
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param msg
     */
    public void displayError(String msg) {
        // TODO - implement Menu.displayError
        throw new UnsupportedOperationException();
    }

    public void clear() {
        // TODO - implement Menu.clear
        throw new UnsupportedOperationException();
    }

    public void close() {
        // TODO - implement Menu.close
        throw new UnsupportedOperationException();
    }

    public void exit() {
        // TODO - implement Menu.exit
        throw new UnsupportedOperationException();
    }

}