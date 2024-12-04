package com.wora.eBanking.services;

import java.util.List;

public interface GenericService <CREATE_DTO,UPDATE_DTO,DTO,ID>{
    DTO save(CREATE_DTO dto);
    DTO update(UPDATE_DTO dto, ID id);
    DTO findById(ID id);
    List<DTO> findAll();
    void delete(ID id);
}
