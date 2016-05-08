package com.otto.beekeeperstocksystem.Repositories;

import java.util.Set;

/**
 * Created by Quam on 4/22/2016.
 */
public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
