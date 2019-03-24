package eu.seatter.homemeasurement.gateway.service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 15/01/2019
 * Time: 14:11
 */

public interface CrudService<T, ID> {

    Set<T> findAll();

    Optional<T> findById(ID id);

    Optional<T> save(T object);

    void delete(T object);

    void deleteById(ID id);
}
