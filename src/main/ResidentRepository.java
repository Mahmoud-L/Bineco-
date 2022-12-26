package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe de repertoire des residents
 */

public class ResidentRepository implements IRepository, Serializable {
    private static final String RES_DATA_FILE_NAME = "src/resources/resData.txt";
    private static ArrayList<Resident> resRep = new ArrayList<>();

    /**
     * Initialiser la valeur du repertoire residents avec la data contenu dans le fichier txt
     */
    public void init() {
        try {
            FileInputStream fis = new FileInputStream(RES_DATA_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.resRep = (ArrayList<Resident>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Avoir l'id d'un resident
     * @param id
     * @return
     */
    public Object get(String id) {
        for (Resident resident : resRep) {
            if (Objects.equals(resident.getId(), id)) {
                return resident;
            }
        }
        return null;
        //Catch exception when object is not found
    }

    /**
     * Avoir la taille du repertoire .
     * @return la taille du repertoire .
     */
    public int size() {
        return resRep.size();
    }

    /**
     * Ajouter d'un nouvau resident dans le repertoire
     * @param entity
     * @return
     */
    public boolean add(Object entity) {
        resRep.add((Resident) entity);
        return true;
        //Catch exception return false
    }

    /**
     * Changer les parametres d'un resident dans le repertoire des residents.
     * @param id
     * @param entity
     */

    @Override
    public boolean edit(String id, Object entity) {
        Resident editRes = (Resident) entity;
        resRep.set(
                resRep.indexOf((Resident) get(id)), editRes
        );
        return true;
        //Catch exception return false
    }

    /**
     * Enlever un resident du repertoire de resident a partir de son id .
     * @param id
     */

    @Override
    public boolean remove(String id) {
        resRep.remove((Resident) get(id));
        return true;
        //Catch exception return false
    }

    @Override
    public String toString() {
        return this.resRep.toString();
    }

    /**
     * Sauvegarder le nouveau repertoire comme repertoire des residents.
     */
    @Override
    public void storeRepo() {
        try {
            File file = new File(RES_DATA_FILE_NAME);
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(resRep);
            oos.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}