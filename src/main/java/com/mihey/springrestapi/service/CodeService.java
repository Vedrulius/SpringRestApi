package com.mihey.springrestapi.service;

import com.mihey.springrestapi.dto.CodeDTO;

import java.util.List;

public interface CodeService {

    List<CodeDTO> getCodes();

    CodeDTO getCodeById(Integer id);

    CodeDTO saveCode(CodeDTO region);

    CodeDTO updateCode(CodeDTO code);

    void deleteCodeById(Integer id);
}

