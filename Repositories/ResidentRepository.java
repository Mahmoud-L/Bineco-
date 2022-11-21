import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe de repertoire des residents
 */

public class ResidentRepository implements IRepository {

    private static ArrayList<Resident> resRep = new ArrayList<Resident>();

    public Object get(String id) {
        for (Resident resident : resRep) {
            if (Objects.equals(resident.getId(), id)) {
                return resident;
            }
        }
        return null;
        //Catch exception when object is not found
    }

    public int size() {
        return resRep.size();
    }

    public boolean add(Object entity) {
        resRep.add((Resident) entity);
        return true;
        //Catch exception return false
    }

    @Override
    public boolean edit(String id, Object entity) {
        Resident editRes = (Resident) entity;
        resRep.set(
                resRep.indexOf((Resident) get(id)), editRes
        );
        return true;
        //Catch exception return false
    }


    @Override
    public boolean remove(String id) {
        resRep.remove((Resident) get(id));
        return true;
        //Catch exception return false
    }

}