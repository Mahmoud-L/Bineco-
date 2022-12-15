package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe de repertoire des utilisateurs (login)
 */

public class UserRepository implements IRepository, Serializable {
    private static final String USER_DATA_FILE_NAME = "src/resources/userData.txt";
    private static ArrayList<User> useRep = new ArrayList<>();

    /**
     * Extracts user data from the userData file.
     */
    public void init() {
        try {
            FileInputStream fis = new FileInputStream(USER_DATA_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.useRep = (ArrayList<User>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Il y a eu une erreur dans la lecture du fichier data, veuillez réessayer plus tard.");
        }
    }

    @Override
    public boolean add(Object entity) {
        if (!(entity instanceof User)) return false;
        this.useRep.add((User) entity);
        return true;
        //Catch exception return false
    }

    @Override
    public boolean edit(String id, Object entity) {
        if (!(entity instanceof User)) return false;
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
    }

    @Override
    public Object get(String id) {
        for (User user : useRep) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }

    public Object has(String u, String p) {
        for (User user : useRep) {
            if (Objects.equals(user.getUsername(), u) && Objects.equals(user.getPwd(), p)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void storeRepo() {
        try {
            File file = new File(USER_DATA_FILE_NAME);
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(useRep);
            oos.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}