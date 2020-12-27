package com.mihey.springrestapi.repository;

import com.mihey.springrestapi.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer,Integer> {
}