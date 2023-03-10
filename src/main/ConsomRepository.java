package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classe de repertoire des consommateurs
 */

public class ConsomRepository implements IRepository, Serializable {
    private static final String CONS_DATA_FILE_NAME = "src/resources/consomData.txt";
    private static ArrayList<Consommateur> conRep = new ArrayList<>();

    /**
     * Initialiser la valeur du repertoire avec la data contenu dans le fichier txt
     */
    public void init() {
        try {
            FileInputStream fis = new FileInputStream(CONS_DATA_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.conRep = (ArrayList<Consommateur>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * Ajouter un consommateur au repertoire des consommateurs.
     * @param entity
     */
    public boolean add(Object entity) {
        this.conRep.add((Consommateur) entity);
        return true;
        //Catch exception return false
    }

    /**
     * Modifier un consommateur dans le repertoire des consommateurs.
     * @param id
     * @param entity
     */
    public boolean edit(String id, Object entity) {
        Consommateur editCon = (Consommateur) entity;
        this.conRep.set(
                conRep.indexOf((Consommateur) get(id)), editCon
        );
        return true;
        //Catch exception return false
    }
    /**
     * Supprimer un consommateur du repertoire des consommateurs.
     * @param id
     */

    public boolean remove(String id) {
        this.conRep.remove((Consommateur) get(id));
        return true;
        //Catch exception return false
    }
    /**
     * Extraire un consommateur du repertoire a partir de son id.
     * @param id
     * @return Le consommateur avec le id correspondant.
     */

    public Object get(String id) {
        for (Consommateur consommateur : conRep) {
            if (Objects.equals(consommateur.getId(), id)) {
                return consommateur;
            }
        }
        return null;
        //Catch exception when object is not found
    }

    /**
     *Filtre la liste de consommateur avec le type du consommateur comme parametre
     * @param cat
     */
    public ArrayList<Consommateur> filterC(String cat) {
        //https://stackoverflow.com/questions/16856554/filtering-an-arraylist-using-an-objects-field
        return (ArrayList<Consommateur>) conRep.stream().filter(consommateur -> consommateur.getType().contains(cat)).collect(Collectors.toList());
    }
    /**
     *Filtre la liste de consommateur avec le nom du consommateur comme parametre
     * @param name
     */
    public ArrayList<Consommateur> filterN(String name) {
        //https://stackoverflow.com/questions/16856554/filtering-an-arraylist-using-an-objects-field
        return (ArrayList<Consommateur>) conRep.stream().filter(consommateur -> consommateur.getName().contains(name)).collect(Collectors.toList());
    }

    /**
     * Sauvegarder le nouveau repertoire comme repertoire des consommateur.
     */
    @Override
    public void storeRepo() {
        try {
            File file = new File(CONS_DATA_FILE_NAME);
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(conRep);
            oos.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}