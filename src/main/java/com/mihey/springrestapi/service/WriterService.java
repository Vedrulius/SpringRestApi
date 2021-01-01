package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.model.dto.WriterDTO;

import java.util.List;

public interface WriterService {
    List<WriterDTO> getWriters();

    WriterDTO getWriterById(Integer id);

    WriterDTO saveWriter(WriterDTO writer);

    WriterDTO updateWriter(WriterDTO writer);

    void deleteWriterById(Integer id);
}
