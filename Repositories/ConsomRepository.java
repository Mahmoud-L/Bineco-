import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classe de repertoire des consommateurs
 */

public class ConsomRepository implements IRepository {

    private static ArrayList<Consommateur> conRep = new ArrayList<Consommateur>();
    public boolean add(Object entity) {
        this.conRep.add((Consommateur) entity);
        return true;
        //Catch exception return false
    }

    public boolean edit(String id, Object entity) {
        Consommateur editCon = (Consommateur) entity;
        this.conRep.set(
                conRep.indexOf((Consommateur) get(id)), editCon
        );
        return true;
        //Catch exception return false
    }

    public boolean remove(String id) {
        this.conRep.remove((Consommateur) get(id));
        return true;
        //Catch exception return false
    }

    public Object get(String id) {
        for (Consommateur consommateur : conRep) {
            if (Objects.equals(consommateur.getId(), id)) {
                return consommateur;
            }
        }
        return null;
        //Catch exception when object is not found
    }

    public ArrayList<Consommateur> filterC(String cat) {
        //https://stackoverflow.com/questions/16856554/filtering-an-arraylist-using-an-objects-field
        return (ArrayList<Consommateur>) conRep.stream().filter(consommateur -> consommateur.getType().contains(cat)).collect(Collectors.toList());
    }
    public ArrayList<Consommateur> filterN(String name) {
        //https://stackoverflow.com/questions/16856554/filtering-an-arraylist-using-an-objects-field
        return (ArrayList<Consommateur>) conRep.stream().filter(consommateur -> consommateur.getName().contains(name)).collect(Collectors.toList());
    }
}