package com.mihey.springrestapi.service.mapper;

import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.dto.WriterDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WriterMapper extends EntityMapper<Writer, WriterDTO> {
    Writer toEntity(WriterDTO writerDTO);

    WriterDTO toDto(Writer writer);

    List<Writer> toEntity(List<WriterDTO> writerDTOS);

    List<WriterDTO> toDto(List<Writer> writers);
}
