package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Writer;

import java.util.List;

public interface WriterService {
    List<Writer> getWriters();

    Writer getWriterById(Integer id);

    Writer saveWriter(Writer region);

    Writer updateWriter(Writer region);

    void deleteWriterById(Integer id);
}
