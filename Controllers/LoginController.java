import java.util.Scanner;

public class LoginController extends Controller {

    static UserRepository useRep = new UserRepository();
    Scanner reader = new Scanner(System.in);

    public LoginController(){
        useRep.add(new User("1","Mokh","Mahmoud"));
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
     */
    public void createUser(String[] infos) {
        // TODO - implement LoginController.createUser
        throw new UnsupportedOperationException();
    }

}