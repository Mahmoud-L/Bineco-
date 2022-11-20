import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class UserRepository implements IRepository {
    private static ArrayList<User> useRep = new ArrayList<User>();
    @Override

    public boolean add(Object entity) {
        this.useRep.add((User) entity);
        return true;
        //Catch exception return false
    }

    @Override
    public boolean edit(String id, Object entity) {
        User editUse = (User) entity;
        this.useRep.set(
                useRep.indexOf((User) get(id)), editUse
        );
        return true;
        //Catch exception return false
    }
    public String getNextId(){
        return Integer.toString(useRep.size());
    }

    @Override
    public boolean remove(String id) {
        this.useRep.remove((User) get(id));
        return true;
        //Catch exception return false
    }

    @Override
    public Object get(String id) {
        for (User user : useRep) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
        //Catch exception when object is not found
    }

    public Object has(String u, String p) {
        for (User user : useRep) {
            if (Objects.equals(user.getUsername(), u) && Objects.equals(user.getPwd(), p)) {
                return user;
            }
        }
        return null;
    }
}