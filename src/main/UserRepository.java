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
     * Initialiser la valeur du repertoire des utilisateurs avec la data contenu dans le fichier txt
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


    /**
     * Ajouter un utilisateur au repertoire des utilisateurs.
     * @param entity
     */
    @Override
    public boolean add(Object entity) {
        if (!(entity instanceof User)) return false;
        this.useRep.add((User) entity);
        return true;
        //Catch exception return false
    }
    /**
     * Modifier l'un des utilisateurs du repertoire.
     * @param id
     * @param entity
     * @return
     */
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
    /**
     * Avoir la taille du repertoire
     * @return la taille du repertoire
     */
    public String getNextId(){
        return Integer.toString(useRep.size());
    }

    /**
     * Enleve un utilisateur du repertoire.
     * @param id
     */
    @Override
    public boolean remove(String id) {
        this.useRep.remove((User) get(id));
        return true;
    }


    /**
     * Extraire l'objet utilisateur du repertoire a partir de son id
     * @param id
     * @return l'objet utilisateur avec l'id donnee en parametres.
     */
    @Override
    public Object get(String id) {
        for (User user : useRep) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Extraire l'utilisateur correspondant au nom d'utilisateur (u) et le mot de passe (p) donnee en parametres.
     * @param u
     * @param p
     * @return
     */
    public Object has(String u, String p) {
        for (User user : useRep) {
            if (Objects.equals(user.getUsername(), u) && Objects.equals(user.getPwd(), p)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Sauvegarder le nouveau repertoire d'utilisateur comme repertoire des utilisateurs.
     */
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