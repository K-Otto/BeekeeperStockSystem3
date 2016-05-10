package com.otto.beekeeperstocksystem.Service.SubLocationService;

import com.otto.beekeeperstocksystem.Domain.SubLocation;

import java.util.Set;

/**
 * Created by Quam on 5/10/2016.
 */
public interface SubLocationService {
    SubLocation addSubLocation(SubLocation subLocation);

    SubLocation updateSubLocation(SubLocation subLocation);

    SubLocation getSubLocation(Long subLocationID);

    Set<SubLocation> getAll();

    SubLocation deleteSubLocation(SubLocation subLocation);

    int deleteAllSubLocation();
}
