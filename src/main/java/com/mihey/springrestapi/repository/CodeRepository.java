package com.mihey.springrestapi.repository;

import com.mihey.springrestapi.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Integer> {
}
