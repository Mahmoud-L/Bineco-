import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ResidentController extends Controller {

    static ResidentRepository resRep = new ResidentRepository();
    Scanner reader = new Scanner(System.in);

    Resident res;

    public ResidentController(String id){
        this.res=(Resident) resRep.get(id);
    }

    public void addBac(String id) {
        System.out.println("Veuillez entrez le code QR du bac");
        String c = reader.next();
        System.out.println("Veuillez entrez le nom du bac");
        String n = reader.next();
        System.out.println("Veuillez entrez le type du bac");
        String t = reader.next();
        res.addBac(new Bac(c, n, t));
    }


    public boolean deleteBac(String code) {
        return res.deleteBac(code);
        //Catch exception return false
    }


    public void viewBacs() {
        ArrayList<Bac> bacs = res.getBacs();
        for (Bac bac : bacs) {
            System.out.println(bac);
        }
        if (bacs.size()==0) {System.out.println("Vous n'avez aucun bac");}
    }

    public void viewMetrics() {

    }

    public boolean setMetrics(String params) {
        return true;
    }

    public void viewMunicipState() {
        // TODO - implement ResidentController.viewMunicipState
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @param text
     */
    public boolean reportProb(String id, String text) {
        return true;
    }

    public void findConsom() {
        // TODO - implement ResidentController.findConsom
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public void rateConsom(String id) {

    }

}