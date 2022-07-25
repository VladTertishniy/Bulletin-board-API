package com.example.bulletinboardapi.service.impl;

import com.example.bulletinboardapi.dto.AdRequestDTO;
import com.example.bulletinboardapi.mapper.AdMapper;
import com.example.bulletinboardapi.model.Ad;
import com.example.bulletinboardapi.dto.AdResponseDTO;
import com.example.bulletinboardapi.repository.AdRepository;
import com.example.bulletinboardapi.service.AdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;

    @Override
    public AdResponseDTO findById(long id) {
        return adMapper.toAdResponseDTO(adRepository.findByIdOrThrow(id));
    }

    @Override
    public AdResponseDTO deleteAd(Long id) {
        Ad ad = adRepository.findByIdOrThrow(id);
        adRepository.deleteById(id);
        return adMapper.toAdResponseDTO(ad);
    }

    @Override
    public AdResponseDTO createAd(AdRequestDTO adRequestDTO) {
        Ad ad = adMapper.toAdEntity(adRequestDTO);
        ad.setCreatedWhen(LocalDateTime.now());
        return adMapper.toAdResponseDTO(adRepository.save(ad));
    }

    @Override
    public List<AdResponseDTO> createAllAd(List<AdRequestDTO> adListRequestDTO) {
        return adMapper.toListAdResponseDTO(adRepository.saveAll(adMapper.toListAdEntityFromAdRequestDTOList(adListRequestDTO)));
    }

    @Override
    public List<AdResponseDTO> getAllAds() {
        return adMapper.toListAdResponseDTO(adRepository.findAll());
    }

}
