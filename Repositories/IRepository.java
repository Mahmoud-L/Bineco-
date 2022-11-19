public interface IRepository {

    /**
     *
     * @param entity
     */
    boolean add(Object entity);

    /**
     *
     * @param id
     * @param entity²
     */
    boolean edit(String id, Object entity);

    /**
     *
     * @param id
     */
    boolean remove(String id);

    /**
     *
     * @param id
     */
    Object get(String id);

}