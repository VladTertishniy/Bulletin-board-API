package com.example.bulletinboardapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
//@Entity(name = "ads")
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdWhen;
    private LocalDateTime updatedWhen;
}
