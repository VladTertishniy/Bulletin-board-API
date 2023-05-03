package com.example.bulletinboardapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

//import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AdRequestDTO {
//    @NotNull(message = "Title is required!")
    private String title;
//    @NotNull(message = "Content is required!")
    private String content;
}
