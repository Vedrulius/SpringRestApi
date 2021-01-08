package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.dto.RegionDTO;
import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.repository.RegionRepository;
import com.mihey.springrestapi.service.mapper.RegionMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegionServiceImplTest {

    @Autowired
    private RegionServiceImpl regionService;
    @MockBean
    private RegionRepository regionRepository;
    @Autowired
    private RegionMapper regionMapper;



    @Test
    void getRegionsTest() {

    }

    @Test
    void getRegionByIdTest() {
    }

    @Test
    void saveRegionTest() {
        Region region = new Region();
        region.setId(1);
        region.setName("RU");
        Mockito.when(regionRepository.save(region)).thenReturn(region);
        assertEquals(region, regionService.saveRegion(region));
        assertNotEquals("US", region.getName());
    }
}