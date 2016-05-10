package com.otto.beekeeperstocksystem.Service.BeekeeperService;

import com.otto.beekeeperstocksystem.Domain.Beekeeper;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public interface BeekeeperService {

        Beekeeper addBeekeeper(Beekeeper beekeeper);

        Beekeeper updateBeekeeper(Beekeeper beekeeper);

        Beekeeper getBeekeeper(Long beekeeperID);

        Set<Beekeeper> getAll();

        Beekeeper deleteBeekeeper(Beekeeper beekeeper);

        int deleteAllBeekeeper();

}
