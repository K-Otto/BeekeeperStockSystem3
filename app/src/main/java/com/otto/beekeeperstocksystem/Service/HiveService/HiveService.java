package com.otto.beekeeperstocksystem.Service.HiveService;

import com.otto.beekeeperstocksystem.Domain.Hive;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public interface HiveService {

    Hive addHive(Hive hive);

    Hive updateHive(Hive hive);

    Hive getHive(Long hiveID);

    Set<Hive> getAll();

    Hive deleteHive(Hive hive);

    int deleteAllHive();
}
