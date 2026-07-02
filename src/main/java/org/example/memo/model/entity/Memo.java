package org.example.memo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.memo.model.request.MemoRequestDto;

// 자바 클래스에서 데이터 베이스의 테이블로 변경 시켜주는 것이다!
// 데이터베이스의 테이블이다!

// JPA의 역할은 데이터 베이스를 자바 세계 안으로 옮기는것!
// 자바 코드 만으로 데이터 베이스를 관리하는 것!

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    public Memo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(MemoRequestDto dto) {
        this.title = (dto.getTitle() == null) ? this.title : dto.getTitle();
        this.content = (dto.getContent() == null) ? this.content : dto.getContent();
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
