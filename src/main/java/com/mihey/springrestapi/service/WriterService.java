package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Writer;

import java.util.List;

public interface WriterService {
    List<Writer> getWriters();

    Writer getWriterById(Integer id);

    Writer saveWriter(Writer writer);

    Writer updateWriter(Writer writer);

    void deleteWriterById(Integer id);
}
