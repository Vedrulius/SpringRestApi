package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.model.dto.RegionDTO;

import java.util.List;

public interface RegionService {

    List<RegionDTO> getRegions();

    RegionDTO getRegionById(Integer id);

    RegionDTO saveRegion(RegionDTO region);

    RegionDTO updateRegion(RegionDTO region);

    void deleteRegionById(Integer id);
}
