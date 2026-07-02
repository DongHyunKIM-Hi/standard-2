package org.example.memo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemoRequestDto {
    private String title;
    private String content;

    // 유효성 검사는 여기 한 곳에서만 관리
    public void validate() {
        if (this.getTitle() == null || this.getTitle().isBlank()) {
            throw new RuntimeException("제목은 비워둘 수 없어요");
        }
        if (this.getTitle().length() > 20) {
            throw new RuntimeException("제목은 20자를 넘을 수 없어요");
        }
        if (this.getContent() == null || this.getContent().isBlank()) {
            throw new RuntimeException("내용은 비워둘 수 없어요");
        }
    }
}