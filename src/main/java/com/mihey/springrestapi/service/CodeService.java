package com.mihey.springrestapi.service;

import com.mihey.springrestapi.dto.CodeDTO;
import com.mihey.springrestapi.model.Code;

import java.util.List;

public interface CodeService {

    List<CodeDTO> getCodes();

    CodeDTO getCodeById(Integer id);

    CodeDTO saveCode(CodeDTO region);

    Code updateCode(Code code);

    void deleteCodeById(Integer id);
}

