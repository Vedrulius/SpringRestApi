package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Region;

import java.util.List;

public interface RegionService {

    List<Region> getRegions();

    Region getRegionById(Integer id);

    Region saveRegion(Region region);

    Region updateRegion(Region region);

    void deleteRegionById(Integer id);
}
