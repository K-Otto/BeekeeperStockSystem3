package com.otto.beekeeperstocksystem.Service.SuperService;

import com.otto.beekeeperstocksystem.Domain.Supers;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public interface SuperService {
    Supers addSupers(Supers supers);

    Supers updateSupers(Supers supers);

    Supers getSupers(Long supersID);

    Set<Supers> getAll();

    Supers deleteSupers(Supers supers);

    int deleteAllSupers();
}
