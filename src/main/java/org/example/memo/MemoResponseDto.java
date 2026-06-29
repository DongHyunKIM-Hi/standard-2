package org.example.memo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoResponseDto {
    private int id;
    private String title;
    private String content;
}