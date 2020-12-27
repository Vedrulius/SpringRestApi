package com.mihey.springrestapi.repository;

import com.mihey.springrestapi.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
