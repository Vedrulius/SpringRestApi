package com.mihey.springrestapi.service.mapper;

import com.mihey.springrestapi.dto.CodeDTO;
import com.mihey.springrestapi.dto.RegionDTO;
import com.mihey.springrestapi.model.Code;
import com.mihey.springrestapi.model.Region;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CodeMapper extends EntityMapper<Code, CodeDTO> {
    Code toEntity(CodeDTO codeDTO);

    CodeDTO toDto(Code code);

    List<Code> toEntity(List<CodeDTO> codeDTOS);

    List<CodeDTO> toDto(List<Code> codes);
}