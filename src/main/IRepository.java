package main;

/**
 * Interfaces des repertoires (database) de Bineco
 */

public interface IRepository {

    /**
     *Ajoute un objet
     * @param entity
     */
    boolean add(Object entity);

    /**
     *Modifier un objet
     * @param id
     * @param entity
     */
    boolean edit(String id, Object entity);

    /**
     *Enlever un objet
     * @param id
     */
    boolean remove(String id);

    /**
     *Extraire l'id
     * @param id
     */
    Object get(String id);

    void storeRepo();

}