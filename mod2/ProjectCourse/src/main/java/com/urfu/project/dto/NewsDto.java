package com.urfu.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NewsDto {
    private Long id;
    private String title;
    private String text;
    private Instant date;

    public NewsDto() {
        date = Instant.now();
    }
}
