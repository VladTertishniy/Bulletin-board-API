package com.example.bulletinboardapi.service;

import com.example.bulletinboardapi.DTO.AdRequestDTO;
import com.example.bulletinboardapi.DTO.AdResponseDTO;

import java.util.List;

public interface AdService {
    AdResponseDTO findById(long id);
    AdResponseDTO deleteAd(Long id);
    AdResponseDTO createAd(AdRequestDTO adRequestDTO);
    List<AdResponseDTO> createAllAd(List<AdRequestDTO> adListRequestDTO);
    List<AdResponseDTO> getAllAds();

}
