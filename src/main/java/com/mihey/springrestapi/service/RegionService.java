package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.model.dto.RegionDTO;

import java.util.List;

public interface RegionService {

    List<RegionDTO> getRegionsDTO();

    Region getRegionById(Integer id);

    Region saveRegion(Region region);

    Region updateRegion(Region region);

    void deleteRegionById(Integer id);
}
