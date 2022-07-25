package com.example.bulletinboardapi.controller;

import com.example.bulletinboardapi.dto.AdRequestDTO;
import com.example.bulletinboardapi.dto.AdResponseDTO;
import com.example.bulletinboardapi.service.AdService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
@AllArgsConstructor
public class AdController {
    private final AdService adService;

    @GetMapping("/getAd/{id}")
    public ResponseEntity<AdResponseDTO> getAd(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adService.findById(id));
    }

    @GetMapping("/getAllAds")
    public ResponseEntity<List<AdResponseDTO>> getAd() {
        return ResponseEntity.status(HttpStatus.OK).body(adService.getAllAds());
    }

    @PostMapping("/createAd")
    public AdResponseDTO createAd(@RequestBody AdRequestDTO adRequestDTO) {
        return adService.createAd(adRequestDTO);
    }

    @GetMapping("/deleteAd/{id}")
    public AdResponseDTO deleteAd(@PathVariable long id) {
        return adService.deleteAd(id);
    }
}
