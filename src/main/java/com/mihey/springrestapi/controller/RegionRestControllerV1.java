package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.service.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestControllerV1 {

    @Autowired
    private RegionServiceImpl regionService;

    @GetMapping("/api/v1/regions")
    public ResponseEntity<List<Region>> getAllRegions(){
        List<Region> regions = regionService.getRegions();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }
}
