package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegionServiceImplTest {

    @Autowired
    private RegionServiceImpl regionService;
    @MockBean
    private RegionRepository regionRepository;

    Region region = new Region();

    {
        region.setId(1);
        region.setName("RU");
    }


    @Test
    void getRegionsTest() {
        List<Region> regions = new ArrayList<>();
        regions.add(region);
        Mockito.when(regionRepository.findAll()).thenReturn(regions);
        assertEquals(regions, regionService.getRegions());
        assertNotEquals(0, regionService.getRegions().size());
    }

    @Test
    void getRegionByIdTest() {
        Optional<Region> r = Optional.of(region);
        Mockito.when(regionRepository.findById(anyInt())).thenReturn(r);
        assertEquals("RU", regionService.getRegionById(1).getName());
        assertNotEquals("US", regionService.getRegionById(1).getName());
    }

    @Test
    void saveRegionTest() {
        Mockito.when(regionRepository.save(region)).thenReturn(region);
        assertEquals(region, regionService.saveRegion(region));
        assertNotEquals("US", region.getName());
    }

    @Test
    public void updateRegionTest() {
        region.setName("GB");
        Mockito.when(regionRepository.save(region)).thenReturn(region);
        assertEquals(region, regionService.updateRegion(region));
        assertNotEquals("RU", regionService.updateRegion(region).getName());
    }

    @Test
    public void deleteRegionByIdTest() {
        Mockito.doNothing().when(regionRepository).deleteById(anyInt());
        regionService.deleteRegionById(1);
    }


}