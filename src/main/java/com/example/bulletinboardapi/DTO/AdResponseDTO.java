package com.example.bulletinboardapi.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdResponseDTO {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdWhen;
    private LocalDateTime updatedWhen;
}
