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

    /**
     *
     * @param user
     */
    public boolean logout(User user) {
        // TODO - implement LoginController.logout
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param user
     * @param pwd
     */
    public boolean changePassword(User user, String pwd) {
        // TODO - implement LoginController.changePassword
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param infos
     * @return
     */
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
                return newCon;
            private String id;
            private String code;
            private String name;
            private String email;
            private String phoneNumber;
            private String address;
            private String type;
            private int capacity;
            private String[] activities;
        }
        return null;
    }
}