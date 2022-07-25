package com.example.bulletinboardapi.mapper;

import com.example.bulletinboardapi.dto.AdRequestDTO;
import com.example.bulletinboardapi.dto.AdResponseDTO;
import com.example.bulletinboardapi.model.Ad;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {
    Ad toAdEntity(AdRequestDTO adRequestDTO);
    AdResponseDTO toAdResponseDTO(Ad ad);
    List<AdResponseDTO> toListAdResponseDTO(List<Ad> ads);
    List<Ad> toListAdEntityFromAdRequestDTOList(List<AdRequestDTO> adRequestDTOList);
    List<Ad> toListAdEntityFromAdResponseDTOList(List<AdResponseDTO> adResponseDTOList);
}
